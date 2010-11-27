package at.tuwien.sbc.model;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class DoodleSchedule implements Serializable {

	private static final long serialVersionUID = -3743685964864677104L;

	private String id;

	private int day;

	private int hour;

	private Peer participant;

	private boolean selected;

	public DoodleSchedule(DoodleEvent event, Peer participant) {
		this.participant = participant;
	}

	public DoodleSchedule() {
		super();
	}

	public DoodleSchedule(int day, int hour, Peer participant) {
		super();
		this.day = day;
		this.hour = hour;
		this.participant = participant;
	}

	public Peer getParticipant() {
		return participant;
	}

	public void setParticipant(Peer participant) {
		this.participant = participant;
	}

	@SpaceId(autoGenerate = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDay() {
		return day;
	}

	public String toString() {
		return "D: " + day + " h: " + getHour();
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getHour() {
		return hour;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}
}
