package be.cools.cqrsevents.domain.show.write.event;

import be.cools.cqrsevents.domain.show.write.ShowAggregateType;
import be.cools.cqrsevents.event.Event;
import be.cools.cqrsevents.event.EventType;

public abstract sealed class ShowEvent extends Event permits ShowStartedEvent, SeasonStartedEvent, EpisodeWatchedEvent, EpisodeRemovedEvent
{

	public ShowEvent( String aggregateId, ShowAggregateType aggregateType, String userId ) {
		super( aggregateId, EventType.SHOW, aggregateType, userId );
	}
}
