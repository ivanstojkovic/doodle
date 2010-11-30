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
		
		Peer p = new Peer("test", "test", "test");
		space.write(p);
		
		p.setAction(null);
		space.write(p);
		
		
		DoodleEvent e = new DoodleEvent();
		e.setName("test");
		e.setAction("test");
		e.setOwner(p.getName());
		space.write(e);
		
		p.addOrganized(e.getName());
		space.write(p);
		
		DoodleSchedule ds = new DoodleSchedule(p.getName(), e.getName());
		space.write(ds);
		
		Peer readPeer = space.read(p);
		if(readPeer == null) {
			System.out.println("NULL");
		}
		
		DoodleEvent readEvent = space.read(e);
		if(readEvent == null) {
			System.out.println("NULL");
		}

		DoodleSchedule readDS = space.read(ds);
		if(readDS == null) {
			System.out.println("NULL");
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
