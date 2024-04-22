

package be.cools.cqrsevents.domain.show.read;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class WatchedEpisode
{
	private final UUID watchId;
	private final UUID episodeId;
	private final Instant watchInstant;
}
