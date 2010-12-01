package at.tuwien.sbc.model;

import java.util.ArrayList;
import java.util.List;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class Peer implements DoodleSpaceObject {
    
    private String name;
    
    private String password;
    
    private List<String> organized;
    
    private List<String> events;
    
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
    
    @SpaceId(autoGenerate = false)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getOrganized() {
        return organized;
    }
    
    public void setOrganized(List<String> organized) {
        this.organized = organized;
    }
    
    public List<String> getEvents() {
        return events;
    }
    
    public void setEvents(List<String> events) {
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
    
    @Override
    public String toString() {
        return "Peer ["+ name + "]";
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
    
    public void addOrganized(String eventId) {
        if (this.organized == null) {
            this.organized = new ArrayList<String>();
        }
        
        this.organized.add(eventId);
    }
    
    public List<String> retrieveOrganized() {
        if (this.organized == null) {
            this.organized = new ArrayList<String>();
        }
        
        return this.organized;
    }
    
    public List<String> retrieveEvents() {
        if (this.events == null) {
            this.events = new ArrayList<String>();
        }
        
        return this.events;
    }
    
    public void addEvent(String eventId) {
    	retrieveEvents().add(eventId);
    }
    
    public String getId() {
    	return name;
    }

	public void setId(String id) {
		this.name = id;
	}
    
}
