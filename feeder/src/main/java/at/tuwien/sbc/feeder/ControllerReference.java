package at.tuwien.sbc.feeder;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openspaces.core.EntryAlreadyInSpaceException;
import org.openspaces.core.GigaSpace;

import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.Peer;

import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.UpdateModifiers;

public class ControllerReference {

	private static Logger logger = Logger.getLogger(ControllerReference.class);

	private static ControllerReference uniqueInstance;

	private GigaSpace gigaSpace;

	private Peer user;

	public static synchronized ControllerReference getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ControllerReference();
		}

		return uniqueInstance;
	}

	private ControllerReference() {

	}

	public void setGigaSpace(GigaSpace gigaSpace) {
		this.gigaSpace = gigaSpace;
	}

	public GigaSpace getGigaSpace() {
		return gigaSpace;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singletons cannot be cloned");
	}

	private void setUser(Peer user) {
		this.user = user;
	}

	public Peer getUser() {
		if (user != null) {
			Peer p = new Peer(user.getName(), null, null);
			p.setEvents(null);
			p.setOrganized(null);
			Peer result = gigaSpace.readIfExists(p);
			System.out.println("Searching for the user [" + user.getName() + "]: " + result);
			return result;
		}
		return null;
	}

	public Peer register(String user, String pass) {
		Peer newPeer = new Peer(user, pass, "register");
		newPeer.setEvents(null);
		newPeer.setOrganized(null);
		LeaseContext<Peer> ctx = this.getGigaSpace().write(newPeer, 1000 * 60 * 60 * 24, 5000,
		        UpdateModifiers.WRITE_ONLY);
		return ctx.getObject();

	}

	public Peer login(String user, String pass) {
		Peer log = new Peer(user, pass, null);
		log.setOrganized(null);
		log.setEvents(null);
		Peer peer = this.getGigaSpace().readIfExists(log);
		if (peer != null) {
			peer.setAction("login");
			this.updateObject(peer);
		} else {
			logger.info("Peer is null");
		}

		this.setUser(peer);
		return peer;

	}

	public List<Peer> searchByName(String regex) {
		Peer template = new Peer();
		Peer[] peers = this.getGigaSpace().readMultiple(template, 100); // get
		// 100

		List<Peer> result = new ArrayList<Peer>();

		for (Peer p : peers) {
			if (p.getName().contains(regex)) {
				result.add(p);
			}
		}

		return result;

	}

	public void logout() {
		Peer u = getUser();
		if (u != null) {
			u.setAction("logout");
			this.updateObject(u);
			this.setUser(null);
		} else {
			logger.warn("USer is null");
		}

	}

	public Peer[] getAllPeers() {
		Peer template = new Peer();

		return this.gigaSpace.readMultiple(template, Integer.MAX_VALUE);
	}

	public void updateObject(Object o) {
		this.gigaSpace.write(o, 1000 * 60 * 60, 5000, UpdateModifiers.UPDATE_ONLY);
	}

	@Deprecated
	public void createEvent(DoodleEvent event) {
		this.getGigaSpace().write(event, 1000 * 60 * 60, 5000, UpdateModifiers.WRITE_ONLY);
		// will throw an exception if the event already exists
	}

	/**
	 * Writes the object to the space. If the object is already there nothing is
	 * done and null is returned.
	 * 
	 * @param o
	 *            the object to write.
	 * @return the object if the write was successful and null otherwise.
	 */
	public Object writeObject(Object o) {
		try {
			LeaseContext<Object> ctx = this.getGigaSpace().write(o, 1000 * 60 * 60, 5000, UpdateModifiers.WRITE_ONLY);
			return o;
		} catch (EntryAlreadyInSpaceException e) {
			ControllerReference.logger.error("Object already in space: " + o.toString());
			return null;
		}

	}

	public List<DoodleEvent> getInvitations() {
		List<DoodleEvent> eventsInvitedTo = new ArrayList<DoodleEvent>();

		DoodleEvent eventTemplate = new DoodleEvent();
		eventTemplate.setInvitations(null);
		eventTemplate.setParticipants(null);
		eventTemplate.setSchedules(null);
		eventTemplate.setFixSchedule(null);
		eventTemplate.setComments(null);

		DoodleEvent[] events = this.getGigaSpace().readMultiple(eventTemplate, 100);
		for (int i = 0; i < events.length; i++) {
			for (String p : events[i].getInvitations()) {
				if (p.equals(user.getName())) {
					eventsInvitedTo.add(events[i]);
				}
			}
		}

		return eventsInvitedTo;
	}

	public List<String> getParticipantsForEvent(DoodleEvent selectedItem) {
		DoodleEvent foundEvent = gigaSpace.readIfExists(selectedItem);
		if (foundEvent == null) {
			return new ArrayList<String>();
		}
		return foundEvent.getParticipants();
	}
}
