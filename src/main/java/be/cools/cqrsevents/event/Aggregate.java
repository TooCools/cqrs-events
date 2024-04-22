package be.cools.cqrsevents.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Slf4j
public abstract class Aggregate<T extends Event, C extends Command>
{

	protected String aggregateId;
	protected int baseRevision = 0;

	protected final List<T> changes = new ArrayList<>();

	public Aggregate( @NonNull String aggregateId, @NonNull List<T> events ) {
		this.aggregateId = aggregateId;
		loadFromHistory( events );
	}

	private void loadFromHistory( List<T> events ) {
		events.forEach(
				event -> {
					applyEvent( event );
					baseRevision = event.getRevision();
				} );
	}

	protected void applyChange( T event ) {
		event.setRevision( baseRevision++ );
		applyEvent( event );
		changes.add( event );
	}

	protected abstract void applyEvent( T event );

	public abstract void processCommand( C command );
}
