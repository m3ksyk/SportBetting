package pl.coderslab.bets.service.scheduled;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.LeagueService;
import pl.coderslab.bets.service.SportService;
import pl.coderslab.bets.service.TeamService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ScheduledTasksService {

    @Autowired
    SportService sportService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    TeamService teamService;

    @Autowired
    GameService gameService;

    ScheduledTasksService() {
        this.createSports();
        this.createLeagues();
        this.createTeams();
        this.regenerateGames();
        this.gameStatusCheck();
        this.resultRigger();
    }

    //for now we only have one sport : football, other sports may be added if functionalities are working
    public void createSports(){
    //METODA WYWALA NULL...
//        List<Sport> checkList = sportService.findAllSports();
//        if(checkList.isEmpty()) {
//        if(sportService.findAllSports() == null){
            Sport football = new Sport();
            football.setName("Football");
            sportService.save(football);
//        }
    }

    //for now we only have one league for one sport, more may be added later if funcs work correctly
    public void createLeagues() {
//        List<League> checkList = leagueService.findAllLeagues();
//        if (checkList.isEmpty()) {
            League footballLeague1 = new League();
            footballLeague1.setName("First Football League of Fakerstan");
            footballLeague1.setSport(sportService.findSportByName("football"));
            leagueService.save(footballLeague1);
//        }
    }
    //for now 20 teams in one league of one sport
    public void createTeams(){
//        List<Team> checkList = teamService.findAllTeams();
//        if (checkList.isEmpty()) {
            Faker faker = new Faker();
            for (int i = 0; i < 20; i++) {
                Team team = new Team();
                team.setName(faker.team().name());
                team.setSport(sportService.findSportByName("football"));
                team.setLeague(leagueService.findLeagueByName("First Football League of Fakerstan"));
                teamService.save(team);
            }
//        }
    }

    //generating 5 new games every 5 minutes
    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void regenerateGames() {
        Faker faker = new Faker();
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        List<Team> teams = teamService.findAllAvailableTeams();

        for (int i = 0; i < 5; i++) {
            Game game = new Game();
            game.setSport(sportService.findSportByName("football"));
            int id1 = random.nextInt(teams.size());
            int id2 = random.nextInt(teams.size());
            while (id1 == id2){
                id2 = random.nextInt(teams.size());
            }
            game.setHomeTeam(teams.get(id1));
            game.setAwayTeam(teams.get(id2));
            teams.remove(id1);
            teams.remove(id2);

            game.setHomeTeamOdd(faker.number().randomDigitNotZero());
            game.setAwayTeamOdd(faker.number().randomDigitNotZero());

            long dateInMillis= calendar.getTimeInMillis();
            Date startDate=new Date(dateInMillis + (TimeUnit.MINUTES.toMinutes(5)));
            Date endDate=new Date(dateInMillis + (TimeUnit.MINUTES.toMinutes(5)));
            game.setStart(startDate);
            game.setEnd(endDate);
            game.setStatus("scheduled");

            gameService.save(game);
        }
    }
    //checks every minute for games going live or finishing
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void gameStatusCheck(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        List<Game> goingLive = gameService.findGamesStarting((java.sql.Date) date, "scheduled");
        List<Game> ending = gameService.findGamesEnding((java.sql.Date) date, "live");

        for (Game g: goingLive) {
            g.setStatus("live");
            gameService.save(g);
        }

        for (Game g: ending) {
            g.setStatus("finished");
            gameService.save(g);
        }
    }
    //add to game results by random every minute
    @Scheduled(cron = "0 1/1 * 1/1 * ?")
    public void resultRigger(){
        //add logic here for setting results for every live game
        List<Game> liveGames = gameService.findGamesByStatus("live");
        Random random = new Random();
        for (Game g : liveGames) {
            g.setAwayTeamScore(g.getAwayTeamScore() + random.nextInt(1));
            g.setHomeTeamScore(g.getAwayTeamScore() + random.nextInt(1));
            gameService.save(g);
        }
    }

}
