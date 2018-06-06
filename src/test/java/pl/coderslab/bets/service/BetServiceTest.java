package pl.coderslab.bets.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.repository.*;
import pl.coderslab.bets.serviceImpl.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BetServiceTest {

    private BetService betService;

    @Mock
    private BetRepository betRepository;

    @Before
    public void setUp() {
        betService = new BetServiceImpl();
    }

    @Test
    public void save() {
        // given
        Bet bet= new Bet();
        Game game = new Game();
        game.setId(1);
        bet.setGame(game);
        List<Bet> bets = new ArrayList<>();
        bets.add(bet);

        // when
        when(betRepository.save(bet)).thenReturn(bet);
        List<Bet> bets2 = new ArrayList<>();
        bets2.add(bet);
        // then
        assertEquals(bets, bets2);

    }

    @Test
    public void findAllUserBets() {
        User user = new User();
        user.setId(1);
        List<Bet> result = new ArrayList<>();
        //when
        when(betRepository.findAllByUserId(1)).thenReturn(result);
        List<Bet> actual = new ArrayList<>();
        //then
        assertEquals(result, actual);
    }

    @Test
    public void findAllByGameId() {
        //given
        Game game = new Game();
        game.setId(1);
        //when
        List<Bet> result = new ArrayList<>();
        when(betRepository.findAllByGameId(1)).thenReturn(result);

        List<Bet> actual = new ArrayList<>();
        //then
        assertEquals(result, actual);
    }

    @Test
    public void findAllLiveUserBets() {
        //given
        User user = new User();
        user.setWallet(BigDecimal.valueOf(1));
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setUsername("username");
        //when
        List<Bet> result = new ArrayList<>();
        when(betRepository.findAllByUserAndPaidOutFalse(user)).thenReturn(result);
        List<Bet> actual = new ArrayList<>();
        //then
        assertEquals(result, actual);
    }
}