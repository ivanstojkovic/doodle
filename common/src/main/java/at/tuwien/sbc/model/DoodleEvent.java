package at.tuwien.sbc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class DoodleEvent implements Serializable {

   // private static final long serialVersionUID = -9105918974900011400L;

	private String id;

	private String name;
	
	private String action;

	private String fixSchedule;

	private String owner;
	
	private List<String> schedules;

	private List<String> invitations;

	private List<String> participants;

	private List<String> comments;

	public DoodleEvent() {
		super();
	}

	@SpaceId(autoGenerate = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFixSchedule() {
		return fixSchedule;
	}

	public void setFixSchedule(String fixSchedule) {
		this.fixSchedule = fixSchedule;
	}

	public List<String> getSchedules() {
		if (schedules == null) {
			schedules = new ArrayList<String>();
		}
		return schedules;
	}

	public void setSchedules(List<String> schedules) {
		this.schedules = schedules;
	}

	public List<String> getInvitations() {
		if (invitations == null) {
			invitations = new ArrayList<String>();
		}
		return invitations;
	}

	public void setInvitations(List<String> invitations) {
		this.invitations = invitations;
	}

	public List<String> getParticipants() {
		if (participants == null) {
			participants = new ArrayList<String>();
		}
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public List<String> getComments() {
		if (comments == null) {
			comments = new ArrayList<String>();
		}
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public String toString() {
		return "[Event: " + this.name + "\nOwner: " + (owner != null ? this.owner.toString() : "null") + "\ninvites: "
				+ Arrays.deepToString(getInvitations().toArray()) + "\nSchedules: " + Arrays.deepToString(getSchedules().toArray()) + "]";
	}

	public void addInvite(Peer peer) {
		if (!getInvitations().contains(peer.getName())) {
			getInvitations().add(peer.getName());
		}
	}
	
	public void addInvite(String peer) {
		if (!getInvitations().contains(peer)) {
			getInvitations().add(peer);
		}
	}

	public void removeInvitation(Peer user) {
		if (getInvitations() != null && getInvitations().contains(user.getName())) {
			getInvitations().remove(user.getName());
			Iterator<String> i = getSchedules().iterator();
			while (i.hasNext()) {
				//TODO
//				DoodleSchedule ds = i.next();
//				if (ds.getParticipant().equals(user.getName())) {
//					i.remove();
//				}
			}
		}
	}

	public void addParticipant(Peer user) {
		if (!getParticipants().contains(user.getName())) {
			getParticipants().add(user.getName());
		}
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoodleEvent other = (DoodleEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setAction(String action) {
	    this.action = action;
    }

	public String getAction() {
	    return action;
    }

}
