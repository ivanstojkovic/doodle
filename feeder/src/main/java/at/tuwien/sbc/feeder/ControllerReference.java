package at.tuwien.sbc.feeder;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;

import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.Peer;

import com.gigaspaces.internal.backport.java.util.Arrays;
import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.UpdateModifiers;

public class ControllerReference {

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
			Peer p = new Peer(user.getName(), user.getPassword(), null);
			p.setEvents(null);
			p.setOrganized(null);
			return gigaSpace.readIfExists(p);
		}
		return null;
	}

	public Peer register(String user, String pass) {
		Peer newPeer = new Peer(user, pass, "register");
		LeaseContext<Peer> ctx = this.getGigaSpace().write(newPeer, 1000 * 60 * 60 * 24, 5000, UpdateModifiers.WRITE_ONLY);

		return ctx.getObject();

	}

	public Peer login(String user, String pass) {
		Peer log = new Peer(user, pass, null);
		log.setOrganized(null);
		log.setEvents(null);
		Peer peer = this.getGigaSpace().readIfExists(log);
		if (peer != null) {
			peer.setAction("login");
			this.getGigaSpace().write(peer);
		} else {
			System.out.println("Peer is null");
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
		Peer u = this.getUser();
		if (u != null) {
			u.setAction("");
			this.updateObject(u);
			this.setUser(null);
		}

	}

	public Peer[] getAllPeers() {
		Peer template = new Peer();

		return this.gigaSpace.readMultiple(template, Integer.MAX_VALUE);
	}

	public void updateObject(Object o) {
		this.gigaSpace.write(o, 1000 * 60 * 60, 50000, UpdateModifiers.UPDATE_ONLY);
	}

	public void createEvent(DoodleEvent event) {
		this.getGigaSpace().write(event, 1000 * 60 * 60, 50000, UpdateModifiers.WRITE_ONLY);
		// will throw an exception if the event already exists
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
			for (Peer p : events[i].getInvitations()) {
				if (p.equals(user)) {
					eventsInvitedTo.add(events[i]);
				}
			}
		}

		return eventsInvitedTo;
	}

	public List<Peer> getParticipantsForEvent(DoodleEvent selectedItem) {
		DoodleEvent foundEvent = gigaSpace.readIfExists(selectedItem);
		if (foundEvent == null) {
			return new ArrayList<Peer>();
		}
		return foundEvent.getParticipants();
	}
}
