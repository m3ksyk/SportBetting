package pl.coderslab.bets.service.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.LeagueService;
import pl.coderslab.bets.service.SportService;
import pl.coderslab.bets.service.TeamService;

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

    public  ArrayList<JSONObject> getLiveGames() throws JSONException {
        final String status = "live";
        return getGamesList(status);
    }

    public  ArrayList<JSONObject> getGamesResults() throws JSONException {
        final String status = "finished";

        return getGamesList(status);
    }

    public ArrayList<JSONObject> getScheduledGames() {
        ArrayList<JSONObject> scheduledGamesJSON = new ArrayList<>();
        List<Game> scheduledGames = gameService.findGamesByStatus("scheduled");
        for (Game g: scheduledGames) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("game_id", g.getId());
            jsonObject.put("sport_name", g.getSport().getName());
            jsonObject.put("league_name", g.getHomeTeam().getName());
            jsonObject.put("game_start", g.getStart());
            jsonObject.put("game_end", g.getEnd());
            jsonObject.put("game_hometeam_name", g.getHomeTeam().getName());
            jsonObject.put("game_awayteam_name", g.getAwayTeam().getName());
            jsonObject.put("game_hometeam_win_odd", g.getHomeTeamWinOdd());
            jsonObject.put("game_hometeam_win_or_draw_odd", g.getHomeTeamWinOrDrawOdd());
            jsonObject.put("game_awayteam_win_odd", g.getAwayTeamWinOdd());
            jsonObject.put("game_awayteam_win_or_draw_odd", g.getAwayTeamWinOrDrawOdd());
            jsonObject.put("game_draw_odd", g.getDrawOdd());
            scheduledGamesJSON.add(jsonObject);
        }
        return scheduledGamesJSON;
    }

    private ArrayList<JSONObject> getGamesList(String status) {
        ArrayList<JSONObject> gamesJSON = new ArrayList<>();
        List<Game> list = gameService.findGamesByStatus(status);

        for (Game g: list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("game_id", g.getId());
            jsonObject.put("sport_name", g.getSport().getName());
            jsonObject.put("league_name", g.getHomeTeam().getName());
            jsonObject.put("game_start", g.getStart());
            jsonObject.put("game_end", g.getEnd());
            jsonObject.put("game_hometeam_name", g.getHomeTeam().getName());
            jsonObject.put("game_hometeam_score", g.getHomeTeamScore());
            jsonObject.put("game_awayteam_name", g.getAwayTeam().getName());
            jsonObject.put("game_awayteam_score", g.getAwayTeamScore());
            jsonObject.put("game_hometeam_win_odd", g.getHomeTeamWinOdd());
            jsonObject.put("game_hometeam_win_or_draw_odd", g.getHomeTeamWinOrDrawOdd());
            jsonObject.put("game_awayteam_win_odd", g.getAwayTeamWinOdd());
            jsonObject.put("game_awayteam_win_or_draw_odd", g.getAwayTeamWinOrDrawOdd());
            jsonObject.put("game_draw_odd", g.getDrawOdd());
            gamesJSON.add(jsonObject);
        }
        return gamesJSON;
    }

    public ArrayList<JSONObject> getLeagues() {
        ArrayList<JSONObject> leaguesJSON= new ArrayList<>();
        List<League> leagues = leagueService.findAllLeagues();
        for (League l: leagues) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("league_id", l.getId());
            jsonObject.put("league_name", l.getName());
            jsonObject.put("league_sport", l.getSport().getName());
            jsonObject.put("league_teams", l.getTeams());
            leaguesJSON.add(jsonObject);
        }
        return leaguesJSON;
    }

    public ArrayList<JSONObject> getSports() {
        ArrayList<JSONObject> sportsJSON = new ArrayList<>();
        List<Sport> sports = sportService.findAllSports();
        for (Sport s: sports) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sport_id", s.getId());
            jsonObject.put("sport_name", s.getName());
            jsonObject.put("sport_leagues", s.getLeagues());
            jsonObject.put("sport_teams", s.getTeams());
            sportsJSON.add(jsonObject);
        }
        return sportsJSON;
    }

    public ArrayList<JSONObject> getTeams() {
        ArrayList<JSONObject> teamsJSON = new ArrayList<>();
        List<Team> teams = teamService.findAllTeams();
        for (Team t: teams) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("team_id", t.getId());
            jsonObject.put("team_name", t.getName());
            jsonObject.put("team_league_name", t.getLeague().getName());
            jsonObject.put("team_sport_name", t.getSport().getName());
            jsonObject.put("team_sport_name", t.getSport().getName());
            jsonObject.put("team_games", t.getGames());
            jsonObject.put("team_games_won", t.getGamesWon());
            jsonObject.put("team_games_lost", t.getGamesLost());
            jsonObject.put("team_draws", t.getDraws());
            jsonObject.put("team_goalsScored", t.getGoalsScored());
            jsonObject.put("team_goalsLost", t.getGoalsLost());
            jsonObject.put("team_table_standing", t.getTableStanding());
            jsonObject.put("team_in_game", t.isInGame());
        }
        return teamsJSON;
    }
}
