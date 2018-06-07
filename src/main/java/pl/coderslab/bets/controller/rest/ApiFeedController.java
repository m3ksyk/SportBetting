package pl.coderslab.bets.controller.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.service.api.JSONProvider;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiFeedController {

    @Autowired
    JSONProvider jsonProvider;

    @GetMapping(path= "/leagues")
    public List<League> getLeaguesAction(@RequestParam("apikey") String value) {
            return jsonProvider.getLeagues();
    }
//    list<xyzDTO> list = DtoService.find...
    @GetMapping(path= "/sports")
    public JSONObject getSportsAction(@RequestParam("apikey") String value) {
            return jsonProvider.getSports();
    }

    @GetMapping(path= "/teams")
    public String getTeamsAction(@RequestParam("apikey") String value) {
            return jsonProvider.getTeams();
    }

    @GetMapping(path= "/live-games")
    public JSONObject getLiveGamesAction(@RequestParam("apikey") String value) {
            return jsonProvider.getLiveGames();
    }

    @GetMapping(path= "/scheduled-games")
    public JSONObject getScheduledGamesAction(@RequestParam("apikey") String value) {
            return jsonProvider.getScheduledGames();
    }

    @GetMapping(path= "/games-results")
    public JSONObject getGamesResultsAction(@RequestParam("apikey") String value) {
            return jsonProvider.getGamesResults();
    }

}
