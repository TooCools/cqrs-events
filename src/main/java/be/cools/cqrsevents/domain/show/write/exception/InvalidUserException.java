
package be.cools.cqrsevents.domain.show.write.exception;

public class InvalidUserException extends RuntimeException
{
	public InvalidUserException( String expectedUserId, String actualUserId ) {
		super( "User ids didn't match expected: %s, actual: %s".formatted( expectedUserId, actualUserId ) );
	}
}
