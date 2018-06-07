package pl.coderslab.bets.service.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.dto.GameDto;
import pl.coderslab.bets.dto.LeagueDto;
import pl.coderslab.bets.dto.SportDto;
import pl.coderslab.bets.dto.TeamDto;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.LeagueService;
import pl.coderslab.bets.service.SportService;
import pl.coderslab.bets.service.TeamService;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOProvider {

    @Autowired
    TeamService teamService;

    @Autowired
    GameService gameService;

    @Autowired
    SportService sportService;

    @Autowired
    LeagueService leagueService;

    /**
     *
     * @return a list of live games DTO
     */
    public List<GameDto> getLiveGames() throws JSONException {
        final String status = "live";
        return getGames(status);

    }
    /**
     *
     * @return  a list of games results DTO
     */
    public List<GameDto> getGamesResults() {
        final String status = "finished";
        return getGames(status);

    }
    /**
     *
     * @return  a list of scheduled games DTO
     */
    public List<GameDto> getScheduledGames() {
        final String status = "scheduled";
        return getGames(status);
    }

    /**
     *
     * @param status - status of the game
     * @return  list of game DTOs
     */
    public List<GameDto> getGames(String status) {
        List<Game> scheduledGames = gameService.findGamesByStatus(status);
        List<GameDto> gameDtoList = new ArrayList<>();
        for (Game g : scheduledGames) {
            GameDto gDto = new GameDto();
            gDto.setId(g.getId());
            gDto.setAwayTeam(g.getAwayTeam());
            gDto.setHomeTeam(g.getHomeTeam());
            gDto.setDrawn(g.isDrawn());
            gDto.setSport(g.getSport());
            gDto.setAwayTeamScore(g.getAwayTeamScore());
            gDto.setHomeTeamScore(g.getHomeTeamScore());
            gDto.setAwayTeamWinOdd(g.getAwayTeamWinOdd());
            gDto.setAwayTeamWinOrDrawOdd(g.getAwayTeamWinOrDrawOdd());
            gDto.setDrawOdd(g.getDrawOdd());
            gDto.setHomeTeamWinOdd(g.getHomeTeamWinOdd());
            gDto.setHomeTeamWinOrDrawOdd(g.getHomeTeamWinOrDrawOdd());
            gDto.setStart(g.getStart());
            gDto.setEnd(g.getEnd());
            gDto.setStatus(g.getStatus());
        }

        return gameDtoList;
    }

    /**
     *
     * @return  a list of leagues DTO
     */
    public List<LeagueDto> getLeagues(){
        List<League> leagues = leagueService.findAllLeagues();
        List<LeagueDto> leagueDtoList = new ArrayList<>();
        for (League l : leagues) {
            LeagueDto lDto = new LeagueDto();
            lDto.setId(l.getId());
            lDto.setName(l.getName());
            lDto.setSport(l.getSport());
            leagueDtoList.add(lDto);
        }

        return leagueDtoList;
    }

    /**
     *
     * @return a list of sports DTO
     */
    public List<SportDto> getSports() {
        List<Sport> sports = sportService.findAllSports();
        List<SportDto> sportDtoList = new ArrayList<>();
        for (Sport s : sports) {
            SportDto sDto = new SportDto();
            sDto.setId(s.getId());
            sDto.setName(s.getName());
            sportDtoList.add(sDto);

        }
        return sportDtoList;
    }

    /**
     *
     * @return a list of Team DTO
     */
    public List<TeamDto> getTeams() {
        List<Team> teams = teamService.findAllTeams();
        List<TeamDto> teamDtoList = new ArrayList<>();
        for (Team t: teams) {
            TeamDto tDto = new TeamDto();
            tDto.setId(t.getId());
            tDto.setName(t.getName());
            tDto.setLeague(t.getLeague());
            tDto.setSport(t.getSport());
            tDto.setGamesWon(t.getGamesWon());
            tDto.setGamesLost(t.getGamesLost());
            tDto.setDraws(t.getDraws());
            tDto.setGoalsScored(t.getGoalsScored());
            tDto.setGoalsLost(t.getGoalsLost());
            tDto.setTableStanding(t.getTableStanding());
            teamDtoList.add(tDto);
        }
        return teamDtoList;
    }
}
