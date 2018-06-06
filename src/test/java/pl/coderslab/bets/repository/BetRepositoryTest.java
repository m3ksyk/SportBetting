package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BetRepository betRepository;

    @Test
    public void shouldFindAllBetsByUserId() {
        //given
        User user = new User();
        user.setId(1);
        //when
        List<Bet> result = betRepository.findAllByUserId(1);
        List<Bet> actual = new ArrayList<>();
        //then
        assertEquals(result, actual);
    }

    @Test
    public void shouldFindAllBetsByGameId() {
        //given
        Game game = new Game();
        game.setId(1);
        //when
        List<Bet> result = betRepository.findAllByGameId(1);
        List<Bet> actual = new ArrayList<>();
        //then
        assertEquals(result, actual);
    }

    @Test
    public void shouldFindAllUserBetsNotPaidOut() {
        //given
        User user = new User();
        user.setWallet(BigDecimal.valueOf(1));
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setUsername("username");
        entityManager.persist(user);
        //when
        List<Bet> result = betRepository.findAllByUserAndPaidOutFalse(user);
        List<Bet> actual = new ArrayList<>();
        //then
        assertEquals(result, actual);
    }
}