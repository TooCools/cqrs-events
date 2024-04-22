package be.cools.cqrsevents.domain.show.write.event;

import be.cools.cqrsevents.domain.show.write.ShowAggregateType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "showId", callSuper = true)
public final class ShowStartedEvent extends ShowEvent
{
	private final UUID showId;

	public ShowStartedEvent( String aggregateId, UUID showId, String userId ) {
		super( aggregateId, ShowAggregateType.SHOW_STARTED, userId);
		this.showId = showId;
	}
}
