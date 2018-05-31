package pl.coderslab.bets.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class DtoController {
    private final Logger logger = LoggerFactory.getLogger(DtoController.class);

//    @Autowired
//    DbUtil dbUtil;

//    @GetMapping(path= "/hello-world")
//    public String helloWorld() {
//        return "Hello World";
//    }

//    @RequestMapping(path= "/get-leagues")
//    public String getLeaguesAction() {
//        String url = "https://apifootball.com/api/?action=get_leagues&APIkey=" +
//                "eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<LeagueDto[]> responseLeagues = restTemplate.getForEntity(
//                url, LeagueDto[].class);
//        LeagueDto[] leagues = responseLeagues.getBody();
//        for (LeagueDto league: leagues) {
//            logger.info("leagues {}", league);
//            LeagueDao.create(league);
//        }
//        return "got leagues";
//    }
//
//    @RequestMapping(path= "/get-country-leagues/{country_id}")
//    public String getLeaguesInCountryAction(@PathVariable long country_id) {
//        String url = "https://apifootball.com/api/?action=get_leagues&country_id=" + country_id +
//                "&APIkey=eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<LeagueDto[]> responseLeagues = restTemplate.getForEntity(
//                url, LeagueDto[].class);
//        LeagueDto[] leagues = responseLeagues.getBody();
//        for (LeagueDto league: leagues) {
//            logger.info("leagues {}", league);
//            LeagueDao.create(league);
//        }
//        return "got leagues in country with id: " + country_id;
//    }
//
//    @RequestMapping(path= "/get-teams/{league_id}")
//    public String getTeamsAction(@PathVariable long league_id){
//        String url="https://apifootball.com/api/?action=get_standings&league_id=" + league_id +
//                "&APIkey=eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<TeamDto[]> responseTeams = restTemplate.getForEntity(
//                url, TeamDto[].class);
//        TeamDto[] teams = responseTeams.getBody();
//        for (TeamDto team: teams) {
//            logger.info("teams {}", team);
//            TeamDao.create(team);
//        }
//        return " Got teams in a league with id:" + league_id;
//    }
//
//    @RequestMapping(path= "/get-standings/{league_id}")
//    public String getStandingsAction(@PathVariable long league_id){
//        String url="https://apifootball.com/api/?action=get_standings&league_id=" + league_id +
//                "&APIkey=eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<StandingsDto[]> responseStandings = restTemplate.getForEntity(
//                url, StandingsDto[].class);
//        StandingsDto[] standings = responseStandings.getBody();
//        for (StandingsDto standing: standings) {
//            logger.info("teams {}", standing);
//            StandingsDao.create(standing);
//        }
//        return " Got team standings in a league with id:" + league_id;
//    }
//
//    @RequestMapping(path= "/get-events/{from}/{to}/{league_id}")
//    public String getGameResultsAction(@PathVariable String from, @PathVariable String to, @PathVariable long league_id){
//        String url="https://apifootball.com/api/?action=get_events&from=" +from+ "&to=" +to+ "&league_id=" +league_id+
//                "&APIkey=eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<GameResultDto[]> responseResults = restTemplate.getForEntity(
//                url, GameResultDto[].class);
//        GameResultDto[] results = responseResults.getBody();
//        for (GameResultDto result: results) {
//            logger.info("results {}", result);
//            GameResultDao.create(result);
//        }
//        return " Got game results from league:" + league_id;
//    }
//
//    @RequestMapping(path= "/get-last-match/{firstTeam}/{secondTeam}")
//    public String getLastMatchAction(@PathVariable String firstTeam, @PathVariable String secondTeam){
//        String url="https://apifootball.com/api/?action=get_H2H&firstTeam=" +firstTeam+ "&secondTeam=" +secondTeam+
//                "&APIkey=eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<GameResultDto[]> responseResults = restTemplate.getForEntity(
//                url, GameResultDto[].class);
//        GameResultDto[] results = responseResults.getBody();
//        for (GameResultDto result: results) {
//            logger.info("results {}", result);
//            GameResultDao.create(result);
//        }
//        return " Got game results from last match between: " + firstTeam + " and " + secondTeam;
//    }
//
//    @RequestMapping(path= "/get-sports")
//    public String getSportsAction() {
//        String url = "https://localhost:8080/api/get-sports";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<SportDto[]> responseSports = restTemplate.getForEntity(
//                url, SportDto[].class);
//        SportDto[] sports = responseSports.getBody();
//        for (SportDto sport: sports) {
//            logger.info("sports {}", sport);
//            SportDao.create(sport);
//        }
//        return "got sports";
//    }

}
