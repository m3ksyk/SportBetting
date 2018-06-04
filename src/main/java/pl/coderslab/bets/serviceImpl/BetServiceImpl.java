package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.repository.BetRepository;
import pl.coderslab.bets.service.BetService;

import java.util.Collections;
import java.util.List;

@Service
public class BetServiceImpl implements BetService {
    @Autowired
    BetRepository betRepository;

    @Override
    public void save(Bet bet) {
        betRepository.save(bet);
    }

    @Override
    public List<Bet> findAllUserBets(long id) {
        return betRepository.findAllByUserId(id);
    }

    @Override
    public List<Bet> findAllByGameId(long id) {
        return betRepository.findAllByGameId(id);
    }
}
