package at.tuwien.sbc.model;

import java.util.List;

public class Peer {

	//how do we generate this?
	private long id;
	
	private String name;
	
	private String password;
	
	private boolean loggedIn;
	
	private PeerType type;
	
	private List<DoodleEvent> organized;
	
	private List<DoodleEvent> events;

	public Peer() {
		super();
	}

	public Peer(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public long getId() {
    	return id;
    }

	public void setId(long id) {
    	this.id = id;
    }

	public String getName() {
    	return name;
    }

	public void setName(String name) {
    	this.name = name;
    }

	public PeerType getType() {
    	return type;
    }

	public void setType(PeerType type) {
    	this.type = type;
    }

	public List<DoodleEvent> getOrganized() {
    	return organized;
    }

	public void setOrganized(List<DoodleEvent> organized) {
    	this.organized = organized;
    }

	public List<DoodleEvent> getEvents() {
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

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
