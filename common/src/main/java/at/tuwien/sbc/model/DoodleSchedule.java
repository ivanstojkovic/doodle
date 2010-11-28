package at.tuwien.sbc.model;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class DoodleSchedule implements Serializable {

	private String id;

	private int day;

	private int hour;

	private String participant;
	
	private String event;

	private boolean selected;

	public DoodleSchedule(String participant, String event) {
		this.participant = participant;
		this.event = event;
	}

	public DoodleSchedule() {
		super();
	}

	public DoodleSchedule(int day, int hour, String participant, String event) {
		super();
		this.day = day;
		this.hour = hour;
		this.participant = participant;
		this.event = event;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
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

	@Override
	public String toString() {
		return "DS[day=" + day + ", hour=" + hour + "]";
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

	public void setEvent(String event) {
	    this.event = event;
    }

	public String getEvent() {
	    return event;
    }
}
