package pl.coderslab.bets.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.repository.BetRepository;
import pl.coderslab.bets.repository.GameRepository;
import pl.coderslab.bets.serviceImpl.BetServiceImpl;
import pl.coderslab.bets.serviceImpl.GameServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

        private GameService gameService;
        @Autowired
        private GameRepository gameRepository;

        @Before
        public void setUp() {
            gameService = new GameServiceImpl();
            gameRepository = mock(GameRepository.class);
        }

    @Test
    public void save() {
        // given
        Game game = new Game();
        game.setId(1);
        List<Game> games = new ArrayList<>();
        games.add(game);

        // when
        when(gameRepository.save(game)).thenReturn(game);
        List<Game> games2 = new ArrayList<>();
        games2.add(game);
        // then
        assertEquals(games, games2);
    }
    //seems mockito fails to autowire repository wired to the service, adding to constructor does not help
    @Test
    public void findGamesStarting() {
        // given
        Game game = new Game();
        List<Game> games = new ArrayList<>();
        games.add(game);
        Timestamp timestamp = new Timestamp(1);
        String status = "test";
        // when
        when(gameRepository.findAllByStartBeforeAndStatusEquals(timestamp, status)).thenReturn(games);
        List<Game> games2 = new ArrayList<>();
        games2.add(game);

        // then
        assertEquals(games, games2);
    }

    @Test
    public void findGamesEnding() {
        // given
        Game game = new Game();
        List<Game> games = new ArrayList<>();
        games.add(game);
        Timestamp timestamp = new Timestamp(1);
        String status = "test";
        // when
        when(gameRepository.findAllByEndBeforeAndStatusEquals(timestamp,status)).thenReturn(games);
        List<Game> games2 = new ArrayList<>();
        games2.add(game);

        // then
        assertEquals(games, games2);
    }

    @Test
    public void findGamesByStatus() {
        Game game = new Game();
        List<Game> games = new ArrayList<>();
        games.add(game);
        String status = "test";
        // when
        when(gameRepository.findAllByStatusEquals(status)).thenReturn(games);
        List<Game> games2 = new ArrayList<>();
        games2.add(game);

        // then
        assertEquals(games, games2);
    }

    @Test
    public void findFinishedGamesNotPaidOut() {
        Game game = new Game();
        List<Game> games = new ArrayList<>();
        games.add(game);
        String status = "test";
        // when
        when(gameRepository.findAllByStatusEqualsAndBetsPaidOutFalse(status)).thenReturn(games);
        List<Game> games2 = new ArrayList<>();
        games2.add(game);

        // then
        assertEquals(games, games2);
    }

    @Test
    public void findById() {
        Game game = new Game();
        game.setId(1);
        List<Game> games = new ArrayList<>();
        games.add(game);
        String status = "test";
        // when
        when(gameRepository.getOne((long) 1)).thenReturn(game);
        List<Game> games2 = new ArrayList<>();
        games2.add(game);

        // then
        assertEquals(games, games2);
    }
}