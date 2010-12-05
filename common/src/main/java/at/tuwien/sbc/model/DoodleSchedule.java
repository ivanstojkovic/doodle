package at.tuwien.sbc.model;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class DoodleSchedule implements DoodleSpaceObject, Comparable<DoodleSchedule> {

	private String id;

	private String day;

	private String hour;

	private String participant;

	private String event;

	private Boolean selected;

	public DoodleSchedule(String participant, String event) {
		this.participant = participant;
		this.event = event;
	}

	public DoodleSchedule() {
		super();
	}

	public DoodleSchedule(String day, String hour, String participant, String event) {
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

	public void setDay(String day) {
		this.day = day;
	}

	public String getDay() {
		return this.day;
	}

	@Override
	public String toString() {
		return "DS[day=" + day + ", hour=" + hour + "]";
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getHour() {
		return hour;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DoodleSchedule other = (DoodleSchedule) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean isSelected() {
		return selected;
	}
	
	

    public int compareTo(DoodleSchedule ds) {

        if (this.day.equals(ds.getDay())) {
            if (this.hour.equals(ds.getHour())) {
                return 0;
            }
            
            if (Integer.parseInt(this.hour) < Integer.parseInt(ds.getHour())) {
                return -1;
            }
            
            return 1;
        }
        
        if (Integer.parseInt(this.day) < Integer.parseInt(ds.getDay())) {
            return -1;
        }
        
        if (Integer.parseInt(this.day) > Integer.parseInt(ds.getDay())) {
            return 1;
        }
        
        return 0;
    }

	public boolean retriveSelected() {
		if(selected == null) {
			return false;
		}
		return selected;
	}

}
