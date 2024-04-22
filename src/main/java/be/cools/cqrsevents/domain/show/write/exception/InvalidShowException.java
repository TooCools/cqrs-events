package be.cools.cqrsevents.domain.show.write.exception;

import java.util.UUID;

public class InvalidShowException extends RuntimeException
{
	public InvalidShowException( UUID expectedShowId, UUID actualShowId ) {
		super( "Show ids didn't match expected: %s, actual: %s".formatted( expectedShowId, actualShowId ) );
	}
}
