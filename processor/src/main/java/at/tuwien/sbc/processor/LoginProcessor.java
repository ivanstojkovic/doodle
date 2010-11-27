package at.tuwien.sbc.processor;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.NotifyType;
import org.openspaces.events.polling.Polling;

import at.tuwien.sbc.model.Peer;

@EventDriven @Polling @NotifyType(update=true) @TransactionalEvent
public class LoginProcessor {
    
    @EventTemplate
    public Peer logPeer() {
        Peer template = new Peer();
        template.setAction("login");
        return template;
        
    }
    
    @SpaceDataEvent
    public Peer eventListener(Peer peer) {
        System.out.println("Peer: " + peer.toString() + " is logged in!");
        System.out.println("Sending notifications");
        peer.setAction("loggedIn");
        return peer;
    }
    
}
