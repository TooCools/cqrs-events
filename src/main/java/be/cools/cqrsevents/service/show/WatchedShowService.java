package be.cools.cqrsevents.service.show;

import be.cools.cqrsevents.domain.show.read.WatchedShow;
import be.cools.cqrsevents.repository.show.WatchedShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WatchedShowService
{
	private final WatchedShowRepository watchedShowRepository;

	public List<WatchedShow> watchedShowsForUser( String userId ) {
		return watchedShowRepository.findByUserId( userId );
	}

	public Optional<WatchedShow> watchedShowForUser( String userId, UUID showId ) {
		return watchedShowRepository.findByUserIdAndShowId( userId, showId );
	}
}
