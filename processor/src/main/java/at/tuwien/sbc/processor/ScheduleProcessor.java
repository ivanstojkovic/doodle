package at.tuwien.sbc.processor;

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
//
//@EventDriven
//@Notify
//@NotifyType(update = true, write = true)
//@TransactionalEvent
public class ScheduleProcessor {
	
	@GigaSpaceContext
	private GigaSpace space;

	@EventTemplate
	public DoodleSchedule templateSchedule() {
		DoodleSchedule template = new DoodleSchedule();
		return template;
	}

	@SpaceDataEvent
	public DoodleSchedule eventListener(DoodleSchedule schedule) {
		DoodleEvent t = new DoodleEvent();
		t.setId(schedule.getEvent());

		DoodleEvent event = this.space.readIfExists(t);
		event.getSchedules().add(schedule.getId());

		return null;
	}
}
