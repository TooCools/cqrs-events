package be.cools.cqrsevents.service.show;

import be.cools.cqrsevents.domain.show.write.event.ShowEvent;
import be.cools.cqrsevents.event.EventRepository;
import be.cools.cqrsevents.event.EventType;
import be.cools.cqrsevents.service.show.event.WatchedShowUpdatedApplicationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShowEventStore
{
	private final EventRepository eventRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	public List<ShowEvent> getEvents( String aggregateId ) {
		return eventRepository.findByAggregateIdAndEventTypeOrderByRevisionAsc( aggregateId, EventType.SHOW.name() ).stream()
		                      .map( ShowEvent.class::cast )
		                      .collect( Collectors.toList() );
	}

	public void save( String aggregateId, List<ShowEvent> changes ) {
		UUID transactionId = UUID.randomUUID();
		changes.forEach( event -> event.setTransactionId( transactionId ) );
		eventRepository.saveAll( changes );

		applicationEventPublisher.publishEvent( new WatchedShowUpdatedApplicationEvent( this, aggregateId, changes ) );

	}
}
