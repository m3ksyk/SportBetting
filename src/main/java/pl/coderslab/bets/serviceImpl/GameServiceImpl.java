package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.repository.GameRepository;
import pl.coderslab.bets.service.GameService;

import java.sql.Date;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public List<Game> findGamesStarting(Date date, String status) {
        return gameRepository.findAllByStartAfterAndStatusEquals(date,status);
    }

    @Override
    public List<Game> findGamesEnding(Date date, String status) {
        return gameRepository.findAllByEndAfterAndStatusEquals(date, status);
    }

    @Override
    public List<Game> findGamesByStatus(String status) {
        return gameRepository.findAllByStatusEquals(status);
    }
}
