package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Game;

import java.sql.Date;
import java.util.List;

public interface GameService {
    void save(Game game);

    List<Game> findGamesStarting(Date date, String status);
    List<Game> findGamesEnding(Date date, String status);

    List<Game> findGamesByStatus(String status);
}
