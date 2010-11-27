package at.tuwien.sbc.processor;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.NotifyType;
import org.openspaces.events.polling.Polling;

import at.tuwien.sbc.model.Peer;

@EventDriven @Polling @NotifyType(update=true, write = true) @TransactionalEvent
public class RegistrationProcessor {
    
    @EventTemplate
    public Peer templatePeer() {
        Peer template = new Peer();
        template.setAction("register");
        return template;
        
    }
    
    @SpaceDataEvent
    public Peer eventListener(Peer peer) {
        System.out.println("Peer: " + peer.toString() + " is now registered!");
        peer.setAction(null);
        return peer;
    }
    
}
