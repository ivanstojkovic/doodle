package at.tuwien.sbc.feeder;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.DoodleSchedule;
import at.tuwien.sbc.model.Peer;

public class EventTest implements InitializingBean, DisposableBean {
	
	@GigaSpaceContext
	private GigaSpace space;

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Cleaning the space");
		this.space.clean();
		
	}

	public void afterPropertiesSet() throws Exception {
		ControllerReference.getInstance().setGigaSpace(space);
		
		Peer p = new Peer("test", "test", "register");
		ControllerReference.getInstance().register(p.getName(), p.getPassword());		
		ControllerReference.getInstance().login(p.getName(), p.getPassword());
		
		Peer current = ControllerReference.getInstance().getUser();
		DoodleEvent event = new DoodleEvent();
		event.setName("test");
		event.setAction("new");
		event.setOwner(current.getName());
		
		ControllerReference.getInstance().writeObject(event);
		
		current = ControllerReference.getInstance().getUser();
		
		if (current == null) {
			System.out.println("NULLLLLLLL!");
		}else{
			System.out.println("AAAAAAAAAAAAAAAAAAAA");
		}
		
		event.setOwner("test");
		ControllerReference.getInstance().writeObject(event);
		
		current = ControllerReference.getInstance().getUser();
		
		if (current == null) {
			System.out.println("NULLLLLLLL!");
		}else{
			System.out.println("AAAAAAAAAAAAAAAAAAAA");
		}
		
		event.setOwner("test");
		ControllerReference.getInstance().writeObject(event);
		
current = ControllerReference.getInstance().getUser();
		
		if (current == null) {
			System.out.println("NULLLLLLLL!");
		}else{
			System.out.println("AAAAAAAAAAAAAAAAAAAA");
		}


		
//		Thread.sleep(3000);
		
		DoodleSchedule day = new DoodleSchedule("a", "a");
		day.setDay("1");
		day.setHour("1");
		ControllerReference.getInstance().writeObject(day);

		
		current = ControllerReference.getInstance().getUser();
		
		if (current == null) {
			System.out.println("NULLLLLLLL!");
		}else{
			System.out.println("AAAAAAAAAAAAAAAAAAAA");
		}
		System.exit(0);
			
	}
	
	/*

// TODO Auto-generated method stub
		Peer p = new Peer("test", "test", "register");
		this.space.write(p);
		
		p.setAction("login");
		this.space.write(p);
		
		DoodleEvent e = new DoodleEvent();
		e.setAction("new");
		e.setName("Test");
		e.setOwner(p.getName());
		
		this.space.write(e);
		Peer t = new Peer("test", null, null);
		t.setEvents(null);
		t.setOrganized(null);
		Peer peer = this.space.readIfExists(t);
		if (peer == null) {
			System.out.println("NULLLLLLLL!");
		}else{
			System.out.println("AAAAAAAAAAAAAAAAAAAA");
		}


	 */

}
