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
public class LogoutProcessor {

	private static Logger logger = Logger.getLogger(LogoutProcessor.class);

	@EventTemplate
	public Peer loginPeer() {
		Peer template = new Peer();
		template.setAction("logout");
		return template;

	}

	@SpaceDataEvent
	public Peer eventListener(Peer peer) {
		logger.info("Peer: " + peer.toString() + " is logged OUT. Setting Action to NULL!");
		logger.info("Peer has events: " + (peer.getEvents() != null ? peer.getEvents().toString() : ""));
		logger.info("Sending notifications");
		peer.setAction(null);
		return peer;
	}

}
