package pl.coderslab.bets.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.bets.dto.GameDto;
import pl.coderslab.bets.dto.LeagueDto;
import pl.coderslab.bets.dto.SportDto;
import pl.coderslab.bets.dto.TeamDto;
import pl.coderslab.bets.service.api.DTOProvider;

import java.util.List;

/**
 * methods in this class provide access points with data
 * The data provided is passed as DTOs, which are auto-converted To JSON
 */
@RestController
@RequestMapping("/api")
public class ApiFeedController {

    @Autowired
    DTOProvider dtoProvider;

    @GetMapping(path= "/leagues")
    @ResponseBody
    public List<LeagueDto> getLeaguesAction(@RequestParam("apikey") String value) {

        return dtoProvider.getLeagues();
    }

    @GetMapping(path= "/sports")
    @ResponseBody
    public List<SportDto> getSportsAction(@RequestParam("apikey") String value) {
        return dtoProvider.getSports();
    }

    @GetMapping(path= "/teams")
    @ResponseBody
    public List<TeamDto> getTeamsAction(@RequestParam("apikey") String value) {
            return dtoProvider.getTeams();
    }

    @GetMapping(path= "/live-games")
    @ResponseBody
    public List<GameDto> getLiveGamesAction(@RequestParam("apikey") String value) {
            return dtoProvider.getLiveGames();
    }

    @GetMapping(path= "/scheduled-games")
    @ResponseBody
    public List<GameDto> getScheduledGamesAction(@RequestParam("apikey") String value) {
            return dtoProvider.getScheduledGames();
    }

    @GetMapping(path= "/games-results")
    @ResponseBody
    public List<GameDto> getGamesResultsAction(@RequestParam("apikey") String value) {
            return dtoProvider.getGamesResults();
    }

}
