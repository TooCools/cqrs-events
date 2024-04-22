package be.cools.cqrsevents.domain.show.write;

import be.cools.cqrsevents.event.AggregateType;

public enum ShowAggregateType implements AggregateType
{
	SHOW_STARTED,
	SEASON_STARTED,
	EPISODE_WATCHED,
	EPISODE_REMOVED

}
