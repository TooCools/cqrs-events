package be.cools.cqrsevents.api.mapper;

import be.cools.cqrsevents.api.model.WatchedEpisode;
import be.cools.cqrsevents.api.model.WatchedSeason;
import be.cools.cqrsevents.api.model.WatchedShow;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiWatchedShowMapper
{
	public WatchedShow map( be.cools.cqrsevents.domain.show.read.WatchedShow source ) {
		return new WatchedShow()
				.id( source.getShowId() )
				.seasons( mapWatchedSeasons( source.getWatchedSeasons() ) );
	}

	private static List<WatchedSeason> mapWatchedSeasons( List<be.cools.cqrsevents.domain.show.read.WatchedSeason> source ) {
		return source.stream()
		             .map( watchedSeason -> new WatchedSeason()
				             .id( watchedSeason.getSeasonId() )
				             .episodes( mapWatchedEpisodes( watchedSeason.getWatchedEpisodes() ) ) )
		             .toList();
	}

	private static List<WatchedEpisode> mapWatchedEpisodes( List<be.cools.cqrsevents.domain.show.read.WatchedEpisode> watchedEpisodes ) {
		return watchedEpisodes.stream()
		                      .map( watchedEpisode -> new WatchedEpisode().id( watchedEpisode.getWatchId() )
		                                                                  .episodeId( watchedEpisode.getEpisodeId() )
		                                                                  .watchTs( watchedEpisode.getWatchInstant().atOffset( ZoneOffset.UTC ) ) )
		                      .collect( Collectors.toList() );
	}

}
