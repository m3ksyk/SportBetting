package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Team;

import java.util.List;

public interface TeamService {
    void save(Team team);

    List<Team> findAllTeams();

    List<Team> findAllAvailableTeams();
}
