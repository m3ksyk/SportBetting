package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Bet;

import java.util.List;

public interface BetService {
    void save(Bet bet);

    List<Bet> findAllUserBets(long id);
}
