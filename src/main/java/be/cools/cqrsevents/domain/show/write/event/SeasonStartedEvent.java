package be.cools.cqrsevents.domain.show.write.event;

import be.cools.cqrsevents.domain.show.write.ShowAggregateType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "seasonId", callSuper = true)
public final class SeasonStartedEvent extends ShowEvent
{
	private final UUID seasonId;

	public SeasonStartedEvent( String aggregateId, UUID seasonId, String userId ) {
		super( aggregateId, ShowAggregateType.SEASON_STARTED, userId );
		this.seasonId = seasonId;
	}
}