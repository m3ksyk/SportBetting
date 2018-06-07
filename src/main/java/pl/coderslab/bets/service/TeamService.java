package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.entity.User;

import java.util.List;

public interface TeamService {
    void save(Team team);

    List<Team> findAllTeams();

    List<Team> findAllAvailableTeams();

    List<Team> findAllTeamsSortedByStanding();

    Team findTeamById(long id);
}
