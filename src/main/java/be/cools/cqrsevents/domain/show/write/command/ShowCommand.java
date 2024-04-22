package be.cools.cqrsevents.domain.show.write.command;

import be.cools.cqrsevents.event.Command;

import java.time.Instant;
import java.util.UUID;

public sealed interface ShowCommand extends Command
{
	record AddShowCommand(String aggregateId, UUID showId, UUID seasonId, UUID episodeId, Instant watchedTs, String userId) implements ShowCommand
	{
	}
}
