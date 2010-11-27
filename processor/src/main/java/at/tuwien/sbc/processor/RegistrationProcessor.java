package at.tuwien.sbc.processor;


import org.apache.log4j.Logger;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;
import org.openspaces.events.notify.NotifyType;

import at.tuwien.sbc.model.Peer;

@EventDriven @Notify @NotifyType(update=true, write = true) @TransactionalEvent
public class RegistrationProcessor {
	
	private static Logger logger = Logger.getLogger(RegistrationProcessor.class);
	
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
