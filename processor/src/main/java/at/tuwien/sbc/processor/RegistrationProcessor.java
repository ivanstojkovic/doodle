package at.tuwien.sbc.processor;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;

import at.tuwien.sbc.model.Peer;

@EventDriven
@Polling
@TransactionalEvent
public class RegistrationProcessor {
    
    private List<Peer> registered;
    
    public RegistrationProcessor() {
        this.registered = new ArrayList<Peer>();
    }
    
    @EventTemplate
    public Peer registeringPeer() {
        Peer peer = new Peer();
        peer.setAction("register");
        
        return peer;
    }
    
    @SpaceDataEvent
    public Peer processCredentialsEvent(Peer peer) {
        System.out.println(peer.toString());
        return this.register(peer);
        
    }
    
    private Peer register(Peer peer) {
        if (this.registered.contains(peer)) {
            System.out.println("Peer found");
            return null;
        } else {
            System.out.println("Registering new peer");
            peer.setAction(null);
            this.registered.add(peer);
            return peer;
        }
    }
}
