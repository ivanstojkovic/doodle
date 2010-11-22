package at.tuwien.sbc.feeder;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;

import at.tuwien.sbc.model.Peer;

import com.gigaspaces.internal.backport.java.util.Arrays;
import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.UpdateModifiers;

public class ControllerReference {

    private static ControllerReference uniqueInstance;
    
    private GigaSpace gigaSpace;
    
    private Peer user;
    
    public static synchronized ControllerReference getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ControllerReference();
        }
        
        return uniqueInstance;
    }
    
    private ControllerReference() {
        
    }

    public void setGigaSpace(GigaSpace gigaSpace) {
        this.gigaSpace = gigaSpace;
    }

    public GigaSpace getGigaSpace() {
        return gigaSpace;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singletons cannot be cloned");
    }

	private void setUser(Peer user) {
	    this.user = user;
    }

	public Peer getUser() {
	    return user;
    }
	
	public Peer register(String user, String pass) {
		Peer newPeer = new Peer(user, pass, "register");
		LeaseContext<Peer> ctx = this.getGigaSpace().write(newPeer,
		        1000 * 60 * 60, 5000, UpdateModifiers.WRITE_ONLY);
		
		return ctx.getObject();
		
	}
	
	public Peer login(String user, String pass) {
		Peer log = new Peer(user, pass, null);
		Peer peer = this.getGigaSpace().readIfExists(log);
		if (peer != null) {
			this.getGigaSpace().write(peer);
		} else {
			System.out.println("Peer is null");
		}
		
		this.setUser(peer);
		return peer;
		
	}
	
	public List<Peer> searchByName(String regex) {
		Peer template = new Peer();
		Peer[] peers = this.getGigaSpace().readMultiple(template, 100); //get 100
		
		List<Peer> result = new ArrayList<Peer>();
		
		for (Peer p : peers) {
			if (p.getName().contains(regex)) {
				result.add(p);
			}
		}
		
		return result;        
         
	}

    public void logout() {
        //is there anything els to do?
        this.setUser(null);
        
    }
}
