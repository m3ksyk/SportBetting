package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Game;

import java.sql.Timestamp;
import java.util.List;

public interface GameService {
    void save(Game game);

    List<Game> findGamesStarting(Timestamp timestamp, String status);
    List<Game> findGamesEnding(Timestamp timestamp, String status);

    List<Game> findGamesByStatus(String status);

    List<Game> findFinishedGamesNotPaidOut(String status);
}
