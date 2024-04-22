package be.cools.cqrsevents.service.show;

import be.cools.cqrsevents.domain.show.read.WatchedShow;
import be.cools.cqrsevents.domain.show.write.WatchedShows;
import be.cools.cqrsevents.domain.show.write.event.ShowEvent;
import be.cools.cqrsevents.repository.show.WatchedShowRepository;
import be.cools.cqrsevents.service.show.event.WatchedShowUpdatedApplicationEvent;
import be.cools.cqrsevents.service.show.mapper.WatchedShowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowEventListener
{
	private final WatchedShowRepository watchedShowRepository;
	private final ShowEventStore showEventStore;
	private final WatchedShowMapper watchedShowMapper;

	@EventListener
	public void listen( WatchedShowUpdatedApplicationEvent event ) {
		List<ShowEvent> events = showEventStore.getEvents( event.getAggregateId() );
		WatchedShows watchedShows = new WatchedShows( event.getAggregateId(), events );
		WatchedShow a = watchedShowRepository.findByUserIdAndShowId( watchedShows.getUserId(), watchedShows.getShowId() )
		                                     .map( saved -> watchedShowMapper.map( saved.getId(), watchedShows ) )
		                                     .orElse( watchedShowMapper.map( watchedShows ) );
		watchedShowRepository.save( a );
	}

}
