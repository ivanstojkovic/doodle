package at.tuwien.sbc.processor;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.events.adapter.SpaceDataEvent;

import at.tuwien.sbc.model.Peer;

public class RegistrationProcessor {
    
    private List<Peer> registered;
    
    public RegistrationProcessor() {
        this.registered = new ArrayList<Peer>();
    }
    
    @SpaceDataEvent
    public Peer register(Peer peer) {
        System.out.println(peer.toString());
        
        if (this.registered.contains(peer)) {
            System.out.println("Peer found");
            return null;
        } else {
            System.out.println("Registering new peer");
          peer.setLoggedIn(true);
          this.registered.add(peer);
          return peer;
        }
    }
}