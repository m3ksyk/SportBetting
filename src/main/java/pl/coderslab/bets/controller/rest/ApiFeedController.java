package pl.coderslab.bets.controller.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.bets.entity.Apikey;
import pl.coderslab.bets.service.ApikeyService;
import pl.coderslab.bets.service.api.JSONProvider;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

@RestController
@RequestMapping("/api")
public class ApiFeedController {

    @Autowired
    JSONProvider jsonProvider;

    @Autowired
    ApikeyService apikeyService;

    @GetMapping(path= "/get-apikey")
    @ResponseBody
    public String getApikey() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[64];
        random.nextBytes(bytes);
        String akString = DatatypeConverter.printHexBinary(bytes).toLowerCase();
        Apikey apikey = new Apikey();
        apikey.setValue(akString);
        apikeyService.save(apikey);
        return "your apikey: " + akString;
    }

    @GetMapping(path= "/get-leagues")
    public JSONObject getLeaguesAction(@RequestParam("apikey") String value) {
        if (apikeyFound(value)) {
            return jsonProvider.getLeagues();
        }else {
            return null;
        }

    }

    @GetMapping(path= "/get-sports")
    public JSONObject getSportsAction(@RequestParam("apikey") String value) {
        if (apikeyFound(value)) {
            return jsonProvider.getSports();
        }else {
            return null;
        }
    }

    @GetMapping(path= "/get-teams")
    public JSONObject getTeamsAction(@RequestParam("apikey") String value) {
        if (apikeyFound(value)) {
            return jsonProvider.getTeams();
        }else {
            return null;
        }
    }

    @GetMapping(path= "/get-live-games")
    public JSONObject getLiveGamesAction(@RequestParam("apikey") String value) {
        if (apikeyFound(value)){
            return jsonProvider.getLiveGames();
        } else {
            return null;
        }
    }

    @GetMapping(path= "/get-scheduled-games")
    public JSONObject getScheduledGamesAction(@RequestParam("apikey") String value) {
        if (apikeyFound(value)){
            return jsonProvider.getScheduledGames();
        } else {
            return null;
        }
    }

    @GetMapping(path= "/get-games-results")
    public JSONObject getGamesResultsAction(@RequestParam("apikey") String value) {
        if (apikeyFound(value)){
            return jsonProvider.getGamesResults();
        } else{
            return null;
        }
    }

    public boolean apikeyFound(String value){
        Apikey apikey = apikeyService.findApiKeyByValue(value);
        if (apikey == null){
            return false;
        }else{
            return true;
        }
    }
}
