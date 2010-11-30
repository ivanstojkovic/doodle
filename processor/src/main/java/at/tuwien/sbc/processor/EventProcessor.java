package at.tuwien.sbc.processor;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;
import org.openspaces.events.notify.NotifyType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.Peer;

import com.j_spaces.core.client.UpdateModifiers;

@EventDriven
@Notify
@NotifyType(update = true, write = true)
//@TransactionalEvent
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
	

	@SpaceDataEvent
	//@Transactional(propagation = Propagation.REQUIRED)
	public DoodleEvent eventListener(DoodleEvent event) {
		logger.info("processing event: " + event.toString());

		Peer t = new Peer(event.getOwner(), null, null);
		t.setEvents(null);
		t.setOrganized(null);
		Peer peer = this.space.readIfExists(t);

		logger.info("Organizer peer: " + peer.toString());

		System.out.println(event.getId());
		peer.addOrganized(event.getId());
		this.space.write(peer);

		event.setAction(null);
		logger.info("fertig");
		return event;
	}


}
