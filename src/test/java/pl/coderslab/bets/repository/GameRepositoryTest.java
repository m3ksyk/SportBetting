package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Game;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private GameRepository gameRepository;


    @Test
    public void findAllByStatusEquals() {
        //given
        String status = "test";
        Game game = new Game();
        game.setStatus(status);
        entityManager.persist(game);
        //when
        List<Game> result = gameRepository.findAllByStatusEquals(status);
        List<Game> actual = new ArrayList<>();
        actual.add(game);
        //then
        assertEquals(result, actual);
    }

    @Test
    public void findAllByEndBeforeAndStatusEquals() {
        //given
        String status = "test";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Timestamp timestamp2 = Timestamp.valueOf(LocalDateTime.now().minusMinutes(1));
        Game game = new Game();
        game.setStatus(status);
        game.setEnd(timestamp2);
        entityManager.persist(game);
        //when
        List<Game> result = gameRepository.findAllByEndBeforeAndStatusEquals(timestamp,status);
        List<Game> actual = new ArrayList<>();
        actual.add(game);
        //then
        assertEquals(result, actual);
    }

    @Test
    public void findAllByStartBeforeAndStatusEquals() {
        //given
        String status = "test";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Timestamp timestamp2 = Timestamp.valueOf(LocalDateTime.now().minusMinutes(1));
        Game game = new Game();
        game.setStatus(status);
        game.setStart(timestamp2);
        entityManager.persist(game);
        //when
        List<Game> result = gameRepository.findAllByEndBeforeAndStatusEquals(timestamp,status);
        List<Game> actual = new ArrayList<>();
        actual.add(game);
        //then
        assertEquals(result, actual);
    }

    @Test
    public void findAllByStatusEqualsAndBetsPaidOutFalse() {
        //given
        String status = "test";
        Game game = new Game();
        game.setStatus(status);
        game.setBetsPaidOut(false);
        entityManager.persist(game);
        //when
        List<Game> result = gameRepository.findAllByStatusEqualsAndBetsPaidOutFalse(status);
        List<Game> actual = new ArrayList<>();
        actual.add(game);
        //then
        assertEquals(result, actual);
    }
}