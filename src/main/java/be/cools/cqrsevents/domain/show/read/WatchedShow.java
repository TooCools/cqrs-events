
package be.cools.cqrsevents.domain.show.read;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class WatchedShow
{
	@Id
	private final UUID id;
	private final UUID showId;
	private final String userId;
	private final List<WatchedSeason> watchedSeasons;
}
