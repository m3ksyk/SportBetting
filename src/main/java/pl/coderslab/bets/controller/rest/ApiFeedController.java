package pl.coderslab.bets.controller.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.bets.JSONProvider;
import pl.coderslab.bets.dto.LeagueDto;
import pl.coderslab.bets.dto.SportDto;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ApiFeedController {

    @Autowired
    JSONProvider jsonProvider;

    //TODO add proper logic for sending json
    //TODO add user api-key verification (or don't, but if you don't remove the fuckin comment)

    @PostMapping(path= "/get-leagues")
    public ArrayList<JSONObject> getLeaguesAction() {
        ArrayList<JSONObject> leagues = jsonProvider.getLeagues();
        return leagues;
    }

    @PostMapping(path= "/get-sports")
    public ArrayList<JSONObject> getSportsAction() {
        ArrayList<JSONObject> sports = jsonProvider.getSports();
        return sports;
    }

    @PostMapping(path= "/get-teams")
    public ArrayList<JSONObject> getTeamsAction() {
        ArrayList<JSONObject> teams = jsonProvider.getTeams();
        return teams;
    }

    @PostMapping(path= "/get-live-games")
    public ArrayList<JSONObject> getLiveGamesAction() {
        ArrayList<JSONObject> liveGames = jsonProvider.getLiveGames();
        return liveGames;
    }

    @PostMapping(path= "/get-scheduled-games")
    public ArrayList<JSONObject> getScheduledGamesAction() {
        ArrayList<JSONObject> scheduledGames = jsonProvider.getScheduledGames();
        return scheduledGames;
    }

    @PostMapping(path= "/get-games-results")
    public ArrayList<JSONObject> getGamesResultsAction() {
        ArrayList<JSONObject> results = jsonProvider.getGamesResults();
        return results;
    }




}
