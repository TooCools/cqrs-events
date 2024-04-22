package be.cools.cqrsevents.api;

import be.cools.cqrsevents.api.mapper.ApiWatchedShowMapper;
import be.cools.cqrsevents.api.model.WatchedEpisodeRequest;
import be.cools.cqrsevents.api.model.WatchedShow;
import be.cools.cqrsevents.domain.show.write.command.ShowCommand;
import be.cools.cqrsevents.service.show.ShowCommandHandler;
import be.cools.cqrsevents.service.show.WatchedShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ShowApiController implements ShowApi
{
	private final ShowCommandHandler showCommandHandler;
	private final WatchedShowService watchedShowService;
	private final ApiWatchedShowMapper apiWatchedShowMapper;

	@Override
	public ResponseEntity<Void> addEpisode( String userId, WatchedEpisodeRequest watchedEpisode ) {
		ShowCommand showCommand = addShowCommand( userId, watchedEpisode );
		showCommandHandler.process( showCommand );
		return ResponseEntity.accepted().build();
	}

	@Override
	public ResponseEntity<WatchedShow> getShow( UUID showId, String userId ) {

		return watchedShowService.watchedShowForUser( userId, showId )
		                         .map( apiWatchedShowMapper::map )
		                         .map( ResponseEntity::ok )
		                         .orElse( ResponseEntity.notFound().build() );
	}

	@Override
	public ResponseEntity<List<WatchedShow>> getShows( String userId ) {
		return ResponseEntity.ok( watchedShowService.watchedShowsForUser( userId )
		                                            .stream()
		                                            .map( apiWatchedShowMapper::map )
		                                            .collect( Collectors.toList() ) );
	}

	private static ShowCommand.AddShowCommand addShowCommand( String userId, WatchedEpisodeRequest watchedEpisode ) {
		String aggregateId = "%s-%s".formatted( userId, watchedEpisode.getShowId().toString() );
		return new ShowCommand.AddShowCommand( aggregateId, watchedEpisode.getShowId(), watchedEpisode.getSeasonId(),
		                                       watchedEpisode.getEpisodeId(), Instant.now(), userId );
	}

}
