package at.tuwien.sbc.processor;

import java.util.logging.Logger;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.NotifyType;
import org.openspaces.events.polling.Polling;

import at.tuwien.sbc.model.Peer;

@EventDriven @Polling @NotifyType(update=true, write = true) @TransactionalEvent
public class RegistrationProcessor {
	
	private static Logger logger = Logger.getLogger(RegistrationProcessor.class.toString());
	
    @EventTemplate
    public Peer templatePeer() {
        Peer template = new Peer();
        template.setAction("register");
        return template;
        
    }
    
    @SpaceDataEvent
    public Peer eventListener(Peer peer) {
    	logger.info("Peer: " + peer.toString() + " is now registered!");
        peer.setAction(null);
        return peer;
    }
    
}
