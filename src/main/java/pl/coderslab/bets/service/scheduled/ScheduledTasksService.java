package pl.coderslab.bets.service.scheduled;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.*;
import pl.coderslab.bets.service.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
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

    @Autowired
    UserService userService;

    @Autowired
    BetService betService;

//    ScheduledTasksService() {
        //odkomentowac jak zacznie dzialac

//        this.regenerateGames();
//        this.gameStatusCheck();
//        this.resultRigger();
//    }

    //for now we only have one sport : football, other sports may be added if functionalities are working
    public void createSports(){
            Sport football = new Sport();
            football.setName("Football");
            sportService.save(football);
    }

    //for now we only have one league for one sport, more may be added later if funcs work correctly
    public void createLeagues() {
            League footballLeague1 = new League();
            footballLeague1.setName("First Football League of Fakerstan");
            footballLeague1.setSport(sportService.findSportByName("football"));
            leagueService.save(footballLeague1);
    }
    //for now 20 teams in one league of one sport
    public void createTeams(){
            Faker faker = new Faker();
            for (int i = 0; i < 20; i++) {
                Team team = new Team();
                team.setName(faker.team().name());
                team.setSport(sportService.findSportByName("football"));
                team.setLeague(leagueService.findLeagueByName("First Football League of Fakerstan"));
                teamService.save(team);
            }
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
            Team home = teams.get(id1);
            Team away = teams.get(id2);

            game.setHomeTeam(home);
            game.setAwayTeam(away);

            home.setInGame(true);
            away.setInGame(true);

            teams.remove(id1);
            teams.remove(id2);

            //for now odds are created by random using faker
            game.setHomeTeamWinOdd(faker.number().randomDouble(2,1,5));
            game.setHomeTeamWinOrDrawOdd(faker.number().randomDouble(2,1,5));
            game.setDrawOdd(faker.number().randomDouble(2,1,5));
            game.setAwayTeamWinOdd(faker.number().randomDouble(2,1,5));
            game.setHomeTeamWinOrDrawOdd(faker.number().randomDouble(2,1,5));

            //setting start and end date for the games
            Timestamp startDate= (Timestamp) new Date(System.currentTimeMillis() + 5 *60*1000);
            Timestamp endDate= (Timestamp) new Date(System.currentTimeMillis() + 10 *60*1000);

            game.setStart(startDate);
            game.setEnd(endDate);
            game.setStatus("scheduled");

            gameService.save(game);
        }
    }
    //checks every minute for games going live or finishing
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void gameStatusCheck(){

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        List<Game> goingLive = gameService.findGamesStarting(timestamp, "scheduled");
        List<Game> ending = gameService.findGamesEnding(timestamp, "live");

        for (Game g: goingLive) {
            g.setStatus("live");
            gameService.save(g);
        }

        for (Game g: ending) {
            Team homeTeam = g.getHomeTeam();
            Team awayTeam = g.getAwayTeam();

            g.setStatus("finished");
            if (g.getAwayTeamScore() > g.getHomeTeamScore()) {
                g.setWinner(awayTeam);
                awayTeam.setGamesWon(awayTeam.getGamesWon() + 1);
                homeTeam.setGoalsLost(homeTeam.getGamesLost() + 1);
            } else if(g.getAwayTeamScore() < g.getHomeTeamScore()){
                g.setWinner(homeTeam);
                awayTeam.setGamesLost(awayTeam.getGamesLost() + 1);
                homeTeam.setGamesWon(homeTeam.getGamesWon() +1 );
            }else{
                g.setWinner(null);
                g.setDrawn(true);
                awayTeam.setDraws(awayTeam.getDraws() +1);
                homeTeam.setDraws(homeTeam.getDraws() +1);
            }

            homeTeam.setInGame(false);
            homeTeam.setGoalsScored(homeTeam.getGoalsScored() + g.getHomeTeamScore());
            homeTeam.setGoalsLost(homeTeam.getGoalsLost() + g.getAwayTeamScore());

            awayTeam.setInGame(false);
            awayTeam.setGoalsScored(awayTeam.getGoalsScored() + g.getAwayTeamScore());
            awayTeam.setGoalsLost(awayTeam.getGoalsLost() + g.getHomeTeamScore());

            teamService.save(homeTeam);
            teamService.save(awayTeam);
            gameService.save(g);

        }
    }
    //add to game results by random every minute
    @Scheduled(cron = "0/30 * * * * ?")
    public void resultRigger(){
        List<Game> liveGames = gameService.findGamesByStatus("live");
        Random random = new Random();
        for (Game g : liveGames) {
            g.setAwayTeamScore(g.getAwayTeamScore() + random.nextInt(2));
            g.setHomeTeamScore(g.getAwayTeamScore() + random.nextInt(2));
            gameService.save(g);
        }
    }

    //finds bets not paid out
    @Scheduled(cron = "0/1 * * * * ?")
    public void gamesPayout(){
        List<Game> finishedGames = gameService.findFinishedGamesNotPaidOut("finished");
     for (Game g : finishedGames) {
            List<Bet> bets = g.getBets();
            Team winner = g.getWinner();
            Boolean draw = g.isDrawn();
            for (Bet b : bets) {
                if(winner.equals(b.getBettingTeam())){
                    payoutSingleBet(b);
                }else if(b.isWillDraw() && draw ==true){
                    payoutSingleBet(b);
                } else{
                    b.setWin(false);
                    b.setPaidOut(true);
                    b.setAmountWon(BigDecimal.valueOf(0));
                    betService.save(b);
                }

            }
            g.setBetsPaidOut(true);
                    gameService.save(g);
        }
    }
    //sorts teams table standing by scored goals
    @Scheduled(cron = "* 0/2 * * * ?")
    public void StandingsCheck(){
        List<Team> teams = teamService.findAllTeams();
        teams.sort((t1, t2) -> t1.getGoalsScored() - t2.getGoalsScored());
        int counter = 1;
        for (Team t : teams) {
            t.setTableStanding(counter);
            counter++;
            teamService.save(t);
        }
    }

    public void payoutSingleBet(Bet b){
        b.setWin(true);
        BigDecimal amount = b.getAmount();
        BigDecimal rate = b.getRate();
        BigDecimal amountWon = amount.multiply(rate);
        b.setAmountWon(amountWon);
        User user = b.getUser();
        BigDecimal current = user.getWallet();
        user.setWallet(current.add(amountWon));
        b.setPaidOut(true);
        userService.save(user);
        betService.save(b);
    }

}
