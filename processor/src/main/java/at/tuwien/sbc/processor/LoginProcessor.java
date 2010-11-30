package at.tuwien.sbc.processor;

import org.apache.log4j.Logger;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;
import org.openspaces.events.notify.NotifyType;

import at.tuwien.sbc.model.Peer;

@EventDriven
@Notify
@NotifyType(update = true)
@TransactionalEvent
public class LoginProcessor {

	private static Logger logger = Logger.getLogger(LoginProcessor.class);

	@EventTemplate
	public Peer loginPeer() {
		Peer template = new Peer();
		template.setAction("login");
		return template;

	}

	@SpaceDataEvent
	public Peer eventListener(Peer peer) {
		logger.info("Peer: " + peer.toString() + " is logged in. Setting Action to LOGGEDIN!");
		logger.info("Peer has events: " + (peer.getEvents() != null ? peer.getEvents().toString() : ""));
		peer.setAction("loggedIn");
		return peer;
	}

}
