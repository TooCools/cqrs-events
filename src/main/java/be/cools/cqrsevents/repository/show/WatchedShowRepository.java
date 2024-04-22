package be.cools.cqrsevents.repository.show;

import be.cools.cqrsevents.domain.show.read.WatchedShow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WatchedShowRepository extends MongoRepository<WatchedShow, UUID>
{
	List<WatchedShow> findByUserId( String userId );

	Optional<WatchedShow> findByUserIdAndShowId( String userId, UUID showId );

}
