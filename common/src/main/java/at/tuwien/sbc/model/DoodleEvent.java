package at.tuwien.sbc.model;

import java.util.List;


public class DoodleEvent {

	private long id;
	
	private String name;
	
	private DoodleSchedule fixSchedule;
	
	private List<DoodleSchedule> schedules;
	
	//how do we manage these...
	//how do we prevent somebody inviting himself...
	//may be make DoodleInvite class with id of invited peer...
	private List<Peer> invitations;
	
	private List<Peer> participants;
	
	private List<DoodleComment> comments;

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

	public DoodleSchedule getFixSchedule() {
    	return fixSchedule;
    }

	public void setFixSchedule(DoodleSchedule fixSchedule) {
    	this.fixSchedule = fixSchedule;
    }

	public List<DoodleSchedule> getSchedules() {
    	return schedules;
    }

	public void setSchedules(List<DoodleSchedule> schedules) {
    	this.schedules = schedules;
    }

	public List<Peer> getInvitations() {
    	return invitations;
    }

	public void setInvitations(List<Peer> invitations) {
    	this.invitations = invitations;
    }

	public List<Peer> getParticipants() {
    	return participants;
    }

	public void setParticipants(List<Peer> participants) {
    	this.participants = participants;
    }

	public List<DoodleComment> getComments() {
    	return comments;
    }

	public void setComments(List<DoodleComment> comments) {
    	this.comments = comments;
    }
}
