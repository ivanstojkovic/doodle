package at.tuwien.sbc.model;

import java.util.Date;

public class DoodleComment implements DoodleSpaceObject {

	private String id;

	private Peer author;

	private String comment;

	private Date date;

	public DoodleComment(Peer author, String comment) {
		super();
		this.author = author;
		this.comment = comment;
		this.date = new Date();
	}

	public DoodleComment() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	// do we need relationship back to the event?

	public String toString() {
		return "[Comment: " + this.comment + " author: " + this.author.getName() + "]";
	}

}
