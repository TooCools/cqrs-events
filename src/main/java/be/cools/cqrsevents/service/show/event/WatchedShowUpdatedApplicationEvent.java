package be.cools.cqrsevents.service.show.event;

import be.cools.cqrsevents.domain.show.write.event.ShowEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class WatchedShowUpdatedApplicationEvent extends ApplicationEvent
{
	private final String aggregateId;
	private final List<ShowEvent> newEvents;

	public WatchedShowUpdatedApplicationEvent( Object source, String aggregateId, List<ShowEvent> newEvents ) {
		super( source );
		this.aggregateId = aggregateId;
		this.newEvents = newEvents;
	}
}
