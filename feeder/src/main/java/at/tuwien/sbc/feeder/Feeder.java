package at.tuwien.sbc.feeder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.SpaceInterruptedException;
import org.openspaces.core.context.GigaSpaceContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import at.tuwien.sbc.common.Data;

/**
 * A feeder bean starts a scheduled task that writes a new Data objects to the
 * space (in an unprocessed state).
 * 
 * <p>
 * The space is injected into this bean using OpenSpaces support for @GigaSpaceContext
 * annotation.
 * 
 * <p>
 * The scheduling uses the java.util.concurrent Scheduled Executor Service. It
 * is started and stopped based on Spring life cycle events.
 * 
 * @author kimchy
 */
public class Feeder implements InitializingBean, DisposableBean {

	private ScheduledExecutorService executorService;

	private ScheduledFuture<?> sf;

	private long numberOfTypes = 10;

	private long defaultDelay = 1000;

	private FeederTask feederTask;

	@GigaSpaceContext
	private GigaSpace gigaSpace;

	public Feeder() {
		new Thread(new GUIFrame()).start();
	}

	/**
	 * Sets the number of types that will be used to set
	 * {@link org.openspaces.example.data.common.Data#setType(Long)}.
	 * 
	 * <p>
	 * The type is used as the routing index for partitioned space. This will
	 * affect the distribution of Data objects over a partitioned space.
	 */
	public void setNumberOfTypes(long numberOfTypes) {
		this.numberOfTypes = numberOfTypes;
	}

	public void setDefaultDelay(long defaultDelay) {
		this.defaultDelay = defaultDelay;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("--- STARTING FEEDER WITH CYCLE [" + defaultDelay
				+ "]");
		executorService = Executors.newScheduledThreadPool(1);
		feederTask = new FeederTask();
		sf = executorService.scheduleAtFixedRate(feederTask, defaultDelay,
				defaultDelay, TimeUnit.MILLISECONDS);
	}

	public void destroy() throws Exception {
		sf.cancel(false);
		sf = null;
		executorService.shutdown();
	}

	public long getFeedCount() {
		return feederTask.getCounter();
	}

	public class FeederTask extends Thread {

		private long counter = 1;

		public void run() {
			try {
				long time = System.currentTimeMillis();
				Data data = new Data((counter++ % numberOfTypes), "FEEDER "
						+ Long.toString(time));
				gigaSpace.write(data);
				System.out.println("--- FEEDER WROTE " + data);
			} catch (SpaceInterruptedException e) {
				// ignore, we are being shutdown
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public long getCounter() {
			return counter;
		}
	}

	class GUIFrame implements Runnable {

		public void run() {
			try {
				JFrame frame = new JFrame("HelloWorldSwing");
				final JLabel label = new JLabel("Hello World");
				frame.getContentPane().add(label);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}	

}
