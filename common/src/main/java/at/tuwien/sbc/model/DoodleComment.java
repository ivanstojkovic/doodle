package at.tuwien.sbc.model;

import java.io.Serializable;
import java.util.Date;

public class DoodleComment implements Serializable {

	private long id;
	
	private Peer author;
	
	private String comment;
	
	private Date date;

	public long getId() {
    	return id;
    }

	public void setId(long id) {
    	this.id = id;
    }

	public Peer getAuthor() {
    	return author;
    }

	public void setAuthor(Peer author) {
    	this.author = author;
    }

	public String getComment() {
    	return comment;
    }

	public void setComment(String comment) {
    	this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
	
	//do we need relationship back to the event?
    
    public String toString() {
        return "[Comment: " + this.comment + " author: " + this.author.getName() + "]";
    }
	
	
}
