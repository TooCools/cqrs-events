

package be.cools.cqrsevents.domain.show.read;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class WatchedSeason
{
	private final UUID seasonId;
	private final List<WatchedEpisode> watchedEpisodes;
}
