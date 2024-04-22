package be.cools.cqrsevents.domain.show.write.event;

public sealed interface ShowEvents {
    record ShowStartedEvent() implements ShowEvents {
    }

    record SeasonStartedEvent() implements ShowEvents {
    }

    record EpisodeWatchedEvent() implements ShowEvents {
    }

    record EpisodeRemovedEvent() implements ShowEvents {
    }
}
