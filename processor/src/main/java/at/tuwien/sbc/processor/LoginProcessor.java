package at.tuwien.sbc.processor;

import java.util.logging.Logger;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.NotifyType;
import org.openspaces.events.polling.Polling;

import at.tuwien.sbc.model.Peer;

@EventDriven @Polling @NotifyType(update=true) @TransactionalEvent
public class LoginProcessor {
	
	private static Logger logger = Logger.getLogger(LoginProcessor.class.toString());
    
    @EventTemplate
    public Peer logPeer() {
        Peer template = new Peer();
        template.setAction("login");
        return template;
        
    }
    
    @SpaceDataEvent
    public Peer eventListener(Peer peer) {
        logger.info("Peer: " + peer.toString() + " is logged in!");
        logger.info("Sending notifications");
        peer.setAction("loggedIn");
        return peer;
    }
    
}
