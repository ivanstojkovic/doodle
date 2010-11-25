package at.tuwien.sbc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    // how do we manage these...
    // how do we prevent somebody inviting himself...
    // may be make DoodleInvite class with id of invited peer...
    private List<Peer> invitations;
    
    private List<Peer> participants;
    
    private List<DoodleComment> comments;
    
    public DoodleEvent() {
        this.schedules = new ArrayList<DoodleSchedule>();
        this.comments = new ArrayList<DoodleComment>();
        this.invitations = new ArrayList<Peer>();
        this.participants = new ArrayList<Peer>();
    }

    @SpaceId(autoGenerate=true)
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
   
    public String toString() {
        return "[Event: " + this.name + "\n" + "invites: " + Arrays.deepToString(this.invitations.toArray()) + "\nSchedules: " + Arrays.deepToString(schedules.toArray()) + "]";
    }

    public void addInvite(Peer peer) {
        if (!this.invitations.contains(peer)) {
            this.invitations.add(peer);
        }
    }
}
