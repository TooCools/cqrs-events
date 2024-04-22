package be.cools.cqrsevents.service.show.mapper;

import be.cools.cqrsevents.domain.show.read.WatchedEpisode;
import be.cools.cqrsevents.domain.show.read.WatchedSeason;
import be.cools.cqrsevents.domain.show.read.WatchedShow;
import be.cools.cqrsevents.domain.show.write.WatchedShows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WatchedShowMapper
{
	public WatchedShow map( WatchedShows source ) {
		return map( UUID.randomUUID(), source );
	}

	public WatchedShow map( UUID id, WatchedShows source ) {
		List<WatchedSeason> watchedSeasons = mapWatchedSeasons( source.getSeasons() );
		return new WatchedShow( id, source.getShowId(), source.getUserId(), watchedSeasons );
	}

	private static List<WatchedSeason> mapWatchedSeasons( Map<UUID, Set<WatchedShows.WatchedEpisode>> seasons ) {
		return seasons.entrySet().stream()
		              .map( entry -> new WatchedSeason( entry.getKey(), mapWatchedEpisodes( entry.getValue() ) ) )
		              .toList();
	}

	private static List<WatchedEpisode> mapWatchedEpisodes( Set<WatchedShows.WatchedEpisode> watchedEpisodes ) {
		return watchedEpisodes.stream()
		                      .map( watchedEpisode -> new WatchedEpisode( watchedEpisode.getWatchId(), watchedEpisode.getEpisodeId(),
		                                                                  watchedEpisode.getWatchInstant() ) )
		                      .collect( Collectors.toList() );
	}
}
