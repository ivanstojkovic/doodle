package at.tuwien.sbc.processor;

import java.util.logging.Logger;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;
import org.openspaces.events.notify.NotifyType;

import com.j_spaces.core.client.UpdateModifiers;

import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.Peer;

//@EventDriven @Notify @NotifyType(update=true, write=true) @TransactionalEvent
public class EventProcessor {
	
	private static Logger logger = Logger.getLogger(EventProcessor.class.toString());
    
	@GigaSpaceContext
	private GigaSpace space;
	
    @EventTemplate
    public DoodleEvent logEvent() {
    	System.out.println("Sending notifications");
        DoodleEvent template = new DoodleEvent();
        return template;
        
    }
    
    @SpaceDataEvent
    public DoodleEvent eventListener(DoodleEvent event) {
    	logger.info("Event: " + event.toString() + " changed!");
        Peer p = event.getOwner();
        Peer t = new Peer(p.getName(), null, null);
        t.setEvents(null);
        t.setOrganized(null);
        Peer peer = this.space.readIfExists(t);
        for (DoodleEvent e : peer.getOrganized()) {
        	if (e.equals(event)) {
        		e = event;
        	}
        }
        
        this.space.write(peer, 1000 * 60 * 60 * 24, 50000, UpdateModifiers.UPDATE_ONLY);
        
        return null;
    }
    
}
