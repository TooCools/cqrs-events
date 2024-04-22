package be.cools.cqrsevents.domain.show.write;

import be.cools.cqrsevents.domain.show.write.command.ShowCommand;
import be.cools.cqrsevents.domain.show.write.event.*;
import be.cools.cqrsevents.domain.show.write.exception.InvalidShowException;
import be.cools.cqrsevents.domain.show.write.exception.InvalidUserException;
import be.cools.cqrsevents.event.Aggregate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.*;

@Getter
public class WatchedShows extends Aggregate<ShowEvent, ShowCommand>
{
	private UUID showId;
	private String userId;
	private Map<UUID, Set<WatchedEpisode>> seasons;

	public WatchedShows( @NonNull String aggregateId, @NonNull List<ShowEvent> events ) {
		super( aggregateId, events );
	}

	@Override
	protected void applyEvent( ShowEvent event ) {
		switch ( event ) {
			case ShowStartedEvent showStartedEvent -> {
				this.showId = showStartedEvent.getShowId();
				this.userId = showStartedEvent.getUserId();
				this.seasons = new HashMap<>();
			}
			case SeasonStartedEvent seasonStartedEvent -> seasons.put( seasonStartedEvent.getSeasonId(), new HashSet<>() );
			case EpisodeWatchedEvent episodeWatchedEvent -> {
				Set<WatchedEpisode> watchedEpisodes = this.seasons.get( episodeWatchedEvent.getSeasonId() );
				watchedEpisodes.add(
						new WatchedEpisode( episodeWatchedEvent.getWatchId(), episodeWatchedEvent.getEpisodeId(), episodeWatchedEvent.getWatchedTs() ) );
			}
			case EpisodeRemovedEvent episodeRemovedEvent -> {
				Set<WatchedEpisode> watchedEpisodes = this.seasons.get( episodeRemovedEvent.getSeasonId() );
				watchedEpisodes.removeIf( watchedEpisode -> watchedEpisode.watchId.equals( episodeRemovedEvent.getWatchId() ) );
			}
		}
	}

	@Override
	public void processCommand( ShowCommand command ) {
		switch ( command ) {
			case ShowCommand.AddShowCommand addShowCommand -> processAddShowCommand( addShowCommand );
		}

	}

	private void processAddShowCommand( ShowCommand.AddShowCommand addShowCommand ) {
		if ( showId != null && !addShowCommand.showId().equals( showId ) ) {
			throw new InvalidShowException( showId, addShowCommand.showId() );
		}
		if ( userId != null && !addShowCommand.userId().equals( userId ) ) {
			throw new InvalidUserException( userId, addShowCommand.userId() );
		}
		if ( showId == null ) {
			applyChange( new ShowStartedEvent( addShowCommand.aggregateId(), addShowCommand.showId(), addShowCommand.userId() ) );
		}
		if ( !seasons.containsKey( addShowCommand.seasonId() ) ) {
			applyChange( new SeasonStartedEvent( addShowCommand.aggregateId(), addShowCommand.seasonId(), addShowCommand.userId() ) );
		}
		applyChange( new EpisodeWatchedEvent( addShowCommand.aggregateId(), UUID.randomUUID(),
		                                      addShowCommand.seasonId(), addShowCommand.episodeId(),
		                                      addShowCommand.watchedTs(), addShowCommand.userId() ) );
	}

	@EqualsAndHashCode(of = { "watchId" })
	@RequiredArgsConstructor
	@Getter
	public static class WatchedEpisode
	{
		private final UUID watchId;
		private final UUID episodeId;
		private final Instant watchInstant;
	}
}
