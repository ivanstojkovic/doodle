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

import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.DoodleSchedule;
import at.tuwien.sbc.model.Notification;
import at.tuwien.sbc.model.Peer;

@EventDriven
@Notify
@NotifyType(update = true, write = true)
@TransactionalEvent
public class EventProcessor {

    private static Logger logger = Logger.getLogger(EventProcessor.class);

    @GigaSpaceContext
    private GigaSpace space;

    @EventTemplate
    public DoodleEvent doodleEvent() {
        DoodleEvent template = new DoodleEvent();
        template.setComments(null);
        template.setInvitations(null);
        template.setParticipants(null);
        template.setSchedules(null);
        template.setAction("processIt");
        return template;

    }

    @SpaceDataEvent
    public DoodleEvent eventListener(DoodleEvent event) {
        logger.info("Event " + event.getName() + " cahnged!");
        updatePeerAsOwner(event);
        updatePeerParticipation(event);
        updateSchedules(event);
        updateInvites(event);
        event.setAction(null);
        return event;
    }

    private void updateInvites(DoodleEvent event) {
        for (String pId : event.retrieveInvitations()) {
            Peer invite = this.space.readIfExists(new Peer(pId, null, null));
            
            if (invite != null) {
                Notification notify = new Notification(invite.getId(), "You are invited to a new Event: " + event.getName());
                this.space.write(notify);
            }
        }
        
    }

    private void updatePeerParticipation(DoodleEvent event) {
        for (String peer : event.retrieveParticipants()) {
            Peer foundParticipant = space.readIfExists(new Peer(peer, null, null));
            if (foundParticipant != null && !foundParticipant.retrieveEvents().contains(event.getId())) {
                logger.info("adding Event " + event.getName() + " to List of Peer[" + foundParticipant.toString()
                        + "] participating Events");
                foundParticipant.addEvent(event.getId());
                space.write(foundParticipant);
            }
        }
    }

    private void updatePeerAsOwner(DoodleEvent event) {
        Peer temp = new Peer(event.getOwner(), null, null);
        Peer foundOwner = space.readIfExists(temp);
        if (foundOwner != null && !foundOwner.retrieveOrganized().contains(event.getId())) {
            logger.info("adding Event " + event.getName() + " to List of Peer[" + foundOwner.toString()
                    + "] organized Events");
            foundOwner.addOrganized(event.getId());
            space.write(foundOwner);
        }
    }

    private void updateSchedules(DoodleEvent event) {
        DoodleSchedule template;
        for (String sId : event.retrieveSchedules()) {
            template = new DoodleSchedule();
            template.setId(sId);
            DoodleSchedule schedule = this.space.readIfExists(template);
            if (schedule != null && schedule.getEvent() != event.getId()) {
                schedule.setEvent(event.getId());
                this.space.write(schedule);
            }
        }
    }

}
