package pl.coderslab.bets.controller.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.bets.service.api.JSONProvider;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ApiFeedController {

    @Autowired
    JSONProvider jsonProvider;

    //TODO add proper logic for sending json (done lrdy?)
    //TODO add user api-key verification (or don't, but if you don't remove the fuckin comment)

    @GetMapping(path= "/get-apikey")
    public String getApikey() {

        return "apikey";
    }

    @GetMapping(path= "/get-leagues")
    public ArrayList<JSONObject> getLeaguesAction() {
        ArrayList<JSONObject> leagues = jsonProvider.getLeagues();
        return leagues;
    }

    @GetMapping(path= "/get-sports")
    public ArrayList<JSONObject> getSportsAction() {
        ArrayList<JSONObject> sports = jsonProvider.getSports();
        return sports;
    }

    @GetMapping(path= "/get-teams")
    public ArrayList<JSONObject> getTeamsAction() {
        ArrayList<JSONObject> teams = jsonProvider.getTeams();
        return teams;
    }

    @GetMapping(path= "/get-live-games")
    public ArrayList<JSONObject> getLiveGamesAction() {
        ArrayList<JSONObject> liveGames = jsonProvider.getLiveGames();
        return liveGames;
    }

    @GetMapping(path= "/get-scheduled-games")
    public ArrayList<JSONObject> getScheduledGamesAction() {
        ArrayList<JSONObject> scheduledGames = jsonProvider.getScheduledGames();
        return scheduledGames;
    }

    @GetMapping(path= "/get-games-results")
    public ArrayList<JSONObject> getGamesResultsAction() {
        ArrayList<JSONObject> results = jsonProvider.getGamesResults();
        return results;
    }
}
