package at.tuwien.sbc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class DoodleEvent implements DoodleSpaceObject {

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

	public DoodleEvent(String id) {
		super();
		this.id = id;
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

	public List<String> getSchedules() {
		return schedules;
	}

	public List<String> getInvitations() {
		return invitations;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public List<String> getComments() {
		return comments;
	}

	public String getFixSchedule() {
		return fixSchedule;
	}

	public void setFixSchedule(String fixSchedule) {
		this.fixSchedule = fixSchedule;
	}

	public List<String> retrieveSchedules() {
		if (schedules == null) {
			schedules = new ArrayList<String>();
		}
		return schedules;
	}

	public void setSchedules(List<String> schedules) {
		this.schedules = schedules;
	}

	public List<String> retrieveInvitations() {
		if (invitations == null) {
			invitations = new ArrayList<String>();
		}
		return invitations;
	}

	public void setInvitations(List<String> invitations) {
		this.invitations = invitations;
	}

	public List<String> retrieveParticipants() {
		if (participants == null) {
			participants = new ArrayList<String>();
		}
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public List<String> retrieveComments() {
		if (comments == null) {
			comments = new ArrayList<String>();
		}
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

//	public String toString() {
//		return "[Event: " + this.name + "Action: " + this.getAction() + "\nOwner: " + (owner != null ? this.owner.toString() : "null")
//				+ "\ninvites: " + Arrays.deepToString(retrieveInvitations().toArray()) + "\nSchedules: "
//				+ Arrays.deepToString(retrieveSchedules().toArray()) + "]";
//	}

	public String toString() {
	    return this.getName();
	}
	
	public boolean addInvite(Peer peer) {
		if (!retrieveInvitations().contains(peer.getName())) {
			return retrieveInvitations().add(peer.getName());
		}
		return false;
	}

	public boolean removeInvitation(Peer user) {
		if (retrieveInvitations().contains(user.getName())) {
		    return retrieveInvitations().remove(user.getName());
		}
		
		return false;
	}

	public void addParticipant(Peer user) {
		if (!retrieveParticipants().contains(user.getName())) {
			retrieveParticipants().add(user.getName());
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

	public void removeComment(String selectedComment) {
		if (retrieveComments().size() > 0) {
			Iterator<String> i = retrieveComments().iterator();
			while (i.hasNext()) {
				String comment = i.next();
				if (comment.equals(selectedComment)) {
					i.remove();
				}
			}
		}
	}

    public void removeSchedules(Peer user, List<DoodleSchedule> schedules) {
        if (retrieveSchedules().size() > 0) {
            Iterator<String> iter = retrieveSchedules().iterator();
            while(iter.hasNext()) {
                String schedule = iter.next();
                for (DoodleSchedule s : schedules) {
                    if (s.getId().equals(s) && s.getParticipant().equals(user.getName())) {
                        iter.remove();
                    }
                }
            }
        }
        
    }

}
