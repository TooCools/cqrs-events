package be.cools.cqrsevents.service.show;

import be.cools.cqrsevents.domain.show.write.WatchedShows;
import be.cools.cqrsevents.domain.show.write.command.ShowCommand;
import be.cools.cqrsevents.domain.show.write.event.ShowEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowCommandHandler
{
	private final ShowEventStore showEventStore;

	public void process( @NonNull ShowCommand showCommand ) {
		String aggregateId = showCommand.aggregateId();
		List<ShowEvent> events = showEventStore.getEvents( aggregateId );

		WatchedShows watchedShows = new WatchedShows( aggregateId, events );
		watchedShows.processCommand( showCommand );

		showEventStore.save(aggregateId, watchedShows.getChanges() );

	}
}
