package pl.coderslab.bets.service.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
public class JSONProvider {

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
     * @return JSON containing list of live games
     * @throws JSONException
     */
    public JSONObject getLiveGames() throws JSONException {
        final String status = "live";
        JSONObject liveGamesJSON = new JSONObject();
        List<Game> liveGames = gameService.findGamesByStatus(status);
        liveGamesJSON.put("liveGames", liveGames);
        return liveGamesJSON;
    }
    /**
     *
     * @return JSON containing a list of games results
     * @throws JSONException
     */
    public JSONObject getGamesResults() throws JSONException {
        final String status = "finished";
        JSONObject finishedGamesJSON = new JSONObject();
        List<Game> finishedGames = gameService.findGamesByStatus(status);
        finishedGamesJSON.put("finishedGames", finishedGames);
        return finishedGamesJSON;
    }
    /**
     *
     * @return JSON containing a list of scheduled games
     * @throws JSONException
     */
    public JSONObject getScheduledGames() throws JSONException {
        final String status = "scheduled";
        JSONObject scheduledGamesJSON = new JSONObject();
        List<Game> scheduledGames = gameService.findGamesByStatus(status);
        scheduledGamesJSON.put("scheduledGames", scheduledGames);
        return scheduledGamesJSON;
    }

    /**
     *
     * @return JSON containing a list of leagues
     * @throws JSONException
     */
    public List<League> getLeagues() throws JSONException {
        JSONObject leaguesJSON= new JSONObject();
        List<League> leagues = leagueService.findAllLeagues();
        return leagues;
//        leaguesJSON.put("leagues", leagues);
//        return leaguesJSON;
    }

    /**
     *
     * @return JSON containing a list of sports
     * @throws JSONException
     */
    public JSONObject getSports() throws JSONException {
        JSONObject sportsJSON = new JSONObject();
        List<Sport> sports = sportService.findAllSports();
        sportsJSON.put("sports", sports);
        return sportsJSON;
    }

    /**
     *
     * @return JSON containing a list of teams
     * @throws JSONException
     */
    public String getTeams() throws JSONException {
        JSONObject teamsJSON = new JSONObject();
        List<Team> teams = teamService.findAllTeams();
        return teams.toString();
//        teamsJSON.put("teams", teams);
//        return teamsJSON;
    }
}
