package be.cools.cqrsevents.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class Event
{
	@Id
	protected UUID eventId;
	protected String aggregateId;
	protected String eventType;
	protected String aggregateType;
	protected String userId;
	protected int revision;
	protected UUID transactionId;
	protected Instant createdDate = Instant.now();

	protected Event( String aggregateId, EventType eventType, AggregateType aggregateType, String userId) {
		this.eventId = UUID.randomUUID();
		this.eventType = eventType.toString();
		this.aggregateId = aggregateId;
		this.aggregateType = aggregateType.toString();
		this.userId=userId;
	}
}
