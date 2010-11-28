package at.tuwien.sbc.processor;

import java.util.List;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;
import org.openspaces.events.notify.NotifyType;

import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.Peer;

import com.j_spaces.core.client.UpdateModifiers;

@EventDriven
@Notify
@NotifyType(update = true, write = true)
@TransactionalEvent
public class EventProcessor {

	private static Logger logger = Logger.getLogger(EventProcessor.class);

	@GigaSpaceContext
	private GigaSpace space;

	@EventTemplate
	public DoodleEvent newTemplate() {
		DoodleEvent template = new DoodleEvent();
		template.setAction("new");
		return template;
	}

	@EventTemplate
	public DoodleEvent updateTemplate() {
		DoodleEvent template = new DoodleEvent();
		template.setAction("update");
		return template;
	}

	@SpaceDataEvent
	public DoodleEvent eventListener(DoodleEvent event) {
		logger.info("Event created: " + event.toString());

		if (event.getAction().equals("new")) {
			Peer t = new Peer(event.getOwner(), null, null);
			t.setEvents(null);
			t.setOrganized(null);
			Peer peer = this.space.readIfExists(t);

			logger.info("Organizer peer: " + peer.toString());

			peer.getOrganized().add(event.getId());
			this.space.write(peer, 1000 * 60 * 60 * 24, 50000, UpdateModifiers.UPDATE_ONLY);
		} else if (event.getAction().equals("update")) {
			// TODO is there anything to do here?
		}
		
		event.setAction(null);
		return event;
	}

}
