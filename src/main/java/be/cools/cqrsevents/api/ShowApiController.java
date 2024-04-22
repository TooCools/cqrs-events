package be.cools.cqrsevents.api;

import be.cools.cqrsevents.api.model.Episode;
import be.cools.cqrsevents.api.model.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShowApiController implements ShowApi {
    @Override
    public ResponseEntity<Void> addEpisode(String userId, Episode episode) {
        return ShowApi.super.addEpisode(userId, episode);
    }

    @Override
    public ResponseEntity<Show> getShow(String showId, String userId) {
        return ShowApi.super.getShow(showId, userId);
    }

    @Override
    public ResponseEntity<List<Show>> getShows(String userId) {
        return ShowApi.super.getShows(userId);
    }

}
