package at.tuwien.sbc.feeder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.SpaceInterruptedException;
import org.openspaces.core.context.GigaSpaceContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import at.tuwien.sbc.common.Data;
import at.tuwien.sbc.feeder.gui.DoodleGUI;

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

	@GigaSpaceContext
	private GigaSpace gigaSpace;

	public Feeder() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DoodleGUI inst = new DoodleGUI(gigaSpace);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}

	public void destroy() throws Exception {
		sf.cancel(false);
		sf = null;
		executorService.shutdown();
	}
}
