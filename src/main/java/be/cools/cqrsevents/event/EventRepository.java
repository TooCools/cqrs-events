package be.cools.cqrsevents.event;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String>
{
	List<Event> findByAggregateIdAndEventTypeOrderByRevisionAsc( String aggregateId, String eventType );
}
