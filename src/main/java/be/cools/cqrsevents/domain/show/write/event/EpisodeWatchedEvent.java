package be.cools.cqrsevents.domain.show.write.event;

import be.cools.cqrsevents.domain.show.write.ShowAggregateType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "watchId", callSuper = true)
public final class EpisodeWatchedEvent extends ShowEvent
{
	private final UUID watchId;
	private final UUID seasonId;
	private final UUID episodeId;
	private final Instant watchedTs;

	public EpisodeWatchedEvent( String aggregateId, UUID watchId, UUID seasonId, UUID episodeId, Instant watchedTs, String userId ) {
		super( aggregateId, ShowAggregateType.EPISODE_WATCHED, userId);
		this.watchId = watchId;
		this.seasonId = seasonId;
		this.episodeId = episodeId;
		this.watchedTs = watchedTs;
	}
}
