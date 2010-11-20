package at.tuwien.sbc.model;

import java.util.Date;

public class DoodleSchedule {

	//do we need an id here?
	private long id;
	
	private Date start;
	
	private Date end;

	public long getId() {
    	return id;
    }

	public void setId(long id) {
    	this.id = id;
    }

	public Date getStart() {
    	return start;
    }

	public void setStart(Date start) {
    	this.start = start;
    }

	public Date getEnd() {
    	return end;
    }

	public void setEnd(Date end) {
    	this.end = end;
    }
	
}
