package at.tuwien.sbc.model;

public class DoodleComment {

	private long id;
	
	private Peer author;
	
	private String comment;

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
	
	//do we need relationship back to the event?
	
	
}
