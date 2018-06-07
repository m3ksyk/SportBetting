package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.repository.TeamRepository;
import pl.coderslab.bets.service.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findAllAvailableTeams() {
        return teamRepository.findAllByInGameFalse();
    }

    @Override
    public List<Team> findAllTeamsSortedByStanding() {
        return teamRepository.findAllOrderByTableStandingDesc();
    }

    @Override
    public Team findTeamById(long id) {
        return teamRepository.getOne(id);
    }

}
