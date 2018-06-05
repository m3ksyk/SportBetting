package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.User;

import java.util.List;

public interface BetService {
    void save(Bet bet);

    List<Bet> findAllUserBets(long id);

    List<Bet> findAllByGameId(long id);

    List<Bet> findAllLiveUserBets(User user);
}
