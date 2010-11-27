package at.tuwien.sbc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class Peer implements Serializable {

	private String name;
    
    private String password;
    
   // private PeerType type;
    
    private List<DoodleEvent> organized;
    
    private List<DoodleEvent> events;
    
    private String action;
    
    public Peer() {
        super();
    }
    
    public Peer(String name, String password, String action) {
        super();
        this.name = name;
        this.password = password;
        this.action = action;
    }
    
    @SpaceId(autoGenerate=false)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
//    public PeerType getType() {
//        return type;
//    }
//    
//    public void setType(PeerType type) {
//        this.type = type;
//    }
    
    public List<DoodleEvent> getOrganized() {
    	if(organized==null) {
    		organized = new ArrayList<DoodleEvent>();
    	}
        return organized;
    }
    
    public void setOrganized(List<DoodleEvent> organized) {
        this.organized = organized;
    }
    
    public List<DoodleEvent> getEvents() {
    	if(events==null) {
    		events = new ArrayList<DoodleEvent>();
    	}
        return events;
    }
    
    public void setEvents(List<DoodleEvent> events) {
        this.events = events;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public String toString() {
        return "[Peer: " + this.getName() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Peer other = (Peer) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    public void notifiyInvite(DoodleEvent evt) {
    	System.out.println("You received an invite for evt " + evt);
    }
    
    public void notifyInviteEdited(DoodleEvent evt) {
    	System.out.println("Event edited: " + evt.toString());
    }
    
    public void notifyInviteRemoved(DoodleEvent evt) {
    	System.out.println("You are no longer invited for event: " + evt.toString());
    }
    
    public void notifyInviteAccepted(boolean accepted) {
    	System.out.println("Invite accepted");
    	//refresh view... 
    	
    }
    
    
}
