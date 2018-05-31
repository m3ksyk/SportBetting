package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.League;

import java.util.List;

public interface LeagueService {
    void save(League league);

    League findLeagueByName(String name);

    List<League> findAllLeagues();
}
