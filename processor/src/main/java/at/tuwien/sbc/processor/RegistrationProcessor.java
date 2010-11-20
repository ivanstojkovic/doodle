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
    public Peer processCredentialsEvent(Peer peer) {
        System.out.println(peer.toString());
        
        if (peer.getAction().equals("register")) {
            return this.register(peer);
        }
        
        return this.login(peer);
        
    }
    
    private Peer login(Peer peer) {
        System.out.println("LOGGING: " + peer.toString());
        return null;
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
