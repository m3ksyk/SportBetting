package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.repository.LeagueRepository;
import pl.coderslab.bets.service.LeagueService;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    LeagueRepository leagueRepository;

    @Override
    public void save(League league) {
        leagueRepository.save(league);
    }

    @Override
    public League findLeagueByName(String name) {
        return leagueRepository.findByNameEquals(name);
    }

    @Override
    public List<League> findAllLeagues() {
        return leagueRepository.findAll();
    }
}
