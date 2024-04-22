package be.cools.cqrsevents.domain.show.write.event;

import be.cools.cqrsevents.domain.show.write.ShowAggregateType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode(of = { "watchId" }, callSuper = true)
@Getter
public final class EpisodeRemovedEvent extends ShowEvent
{
	private final UUID watchId;
	private final UUID seasonId;

	public EpisodeRemovedEvent( String aggregateId, UUID watchId, UUID seasonId, String userId ) {
		super( aggregateId, ShowAggregateType.EPISODE_REMOVED, userId);
		this.watchId = watchId;
		this.seasonId = seasonId;
	}
}
