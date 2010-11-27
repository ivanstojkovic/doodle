package at.tuwien.sbc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceProperty;

@SpaceClass
public class DoodleEvent implements Serializable {

	private String id;

	private String name;

	private DoodleSchedule fixSchedule;

	private List<DoodleSchedule> schedules;

	private Peer owner;

	// how do we manage these...
	// how do we prevent somebody inviting himself...
	// may be make DoodleInvite class with id of invited peer...
	private List<Peer> invitations;

	private List<Peer> participants;

	private List<DoodleComment> comments;

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

	public DoodleSchedule getFixSchedule() {
		return fixSchedule;
	}

	public void setFixSchedule(DoodleSchedule fixSchedule) {
		this.fixSchedule = fixSchedule;
	}

	public List<DoodleSchedule> getSchedules() {
		if (schedules == null) {
			schedules = new ArrayList<DoodleSchedule>();
		}
		return schedules;
	}

	public void setSchedules(List<DoodleSchedule> schedules) {
		this.schedules = schedules;
	}

	public List<Peer> getInvitations() {
		if (invitations == null) {
			invitations = new ArrayList<Peer>();
		}
		return invitations;
	}

	public void setInvitations(List<Peer> invitations) {
		this.invitations = invitations;
	}

	public List<Peer> getParticipants() {
		if (participants == null) {
			participants = new ArrayList<Peer>();
		}
		return participants;
	}

	public void setParticipants(List<Peer> participants) {
		this.participants = participants;
	}

	public List<DoodleComment> getComments() {
		if (comments == null) {
			comments = new ArrayList<DoodleComment>();
		}
		return comments;
	}

	public void setComments(List<DoodleComment> comments) {
		this.comments = comments;
	}

	public String toString() {
		return "[Event: " + this.name + "\nOwner: " + (owner != null ? this.owner.toString() : "null") + "\ninvites: "
				+ Arrays.deepToString(getInvitations().toArray()) + "\nSchedules: " + Arrays.deepToString(getSchedules().toArray()) + "]";
	}

	public void addInvite(Peer peer) {
		if (!getInvitations().contains(peer)) {
			getInvitations().add(peer);
		}
	}

	public void removeInvitation(Peer user) {
		if (getInvitations() != null && getInvitations().contains(user)) {
			getInvitations().remove(user);
			Iterator<DoodleSchedule> i = getSchedules().iterator();
			while (i.hasNext()) {
				DoodleSchedule ds = i.next();
				if (ds.getParticipant().equals(user)) {
					i.remove();
				}
			}
		}
	}

	public void addParticipant(Peer user) {
		if (!getParticipants().contains(user)) {
			getParticipants().add(user);
		}
	}

	public void setOwner(Peer owner) {
		this.owner = owner;
	}

	public Peer getOwner() {
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

}
