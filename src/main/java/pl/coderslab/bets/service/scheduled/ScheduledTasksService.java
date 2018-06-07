package pl.coderslab.bets.service.scheduled;

import com.github.javafaker.Faker;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.*;
import pl.coderslab.bets.service.*;

import javax.jms.*;
import javax.jms.Message;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    MessageService messageService;

    /**
     * method createSports is utilized to populate database with objects of entity Sport.
     * The current build of the application supports only one sport: Football
     */
    public void createSports() {
        Sport football = new Sport();
        football.setName("Football");
        sportService.save(football);
    }

    /**
     * method createLeagues is utilized to populate database with objects of entity League.
     * The current build of the application supports only one league
     */
    public void createLeagues() {
        League footballLeague1 = new League();
        footballLeague1.setName("First Football League of Fakerstan");
        footballLeague1.setSport(sportService.findSportByName("football"));
        leagueService.save(footballLeague1);
    }

    /**
     * method createTeams is utilized to populate the database with objects of entity Team.
     * In the current build, the method inserts 20 randomly generated teams, linked to created Sports and Leagues
     */
    public void createTeams() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Team team = new Team();
            team.setName(faker.team().name());
            team.setSport(sportService.findSportByName("football"));
            team.setLeague(leagueService.findLeagueByName("First Football League of Fakerstan"));
            teamService.save(team);
        }
    }

    /**
     * method regenerate games generates events based on a  schedule.
     * Every 5 minutes, five new games are created. A new object Game() is created.
     * The teams are chosen by random from a collection of teams available (teams that currently are not in-game).
     * After choosing the teams and adding them to the game, the odds are generated using setOdd() method.
     * Odd generation is based on Java Faker random number generation.
     * The time for the game to start and end is set:
     *      start - current time + 5 minutes
     *      end - current time + 10 minutes
     * The status of the created game is set to "scheduled".
     * The status of the game is checked by method gameStatusCheck() .
     * After generation, the status of teams and the game is persisted.
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void regenerateGames() {
        Random random = new Random();
        List<Team> teams = teamService.findAllAvailableTeams();
        if (teams.size() > 2) {

            for (int i = 0; i < 5; i++) {
                Game game = new Game();

                //setting start and end date for the games
                Timestamp startDate = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5));
                Timestamp endDate = Timestamp.valueOf(LocalDateTime.now().plusMinutes(10));

                //choosing teams by random
                game.setSport(sportService.findSportByName("football"));
                int id1 = random.nextInt(teams.size()-1);
                int id2 = random.nextInt(teams.size()-1);
                while (id1 == id2) {
                    id2 = random.nextInt(teams.size()-1);
                }
                Team home = teams.get(id1);
                Team away = teams.get(id2);

                game.setHomeTeam(home);
                game.setAwayTeam(away);

                home.setInGame(true);
                away.setInGame(true);

                //notify teams subscribers about incoming matches
                notifySubscribers(home, away, startDate);
                notifySubscribers(away, home, startDate);

                teams.remove(id1);
                teams.remove(id2);

                //for now odds are created by random using faker
                game.setHomeTeamWinOdd(setOdd());
                game.setHomeTeamWinOrDrawOdd(setOdd());
                game.setDrawOdd(setOdd());
                game.setAwayTeamWinOdd(setOdd());
                game.setAwayTeamWinOrDrawOdd(setOdd());

                game.setStart(startDate);
                game.setEnd(endDate);
                game.setStatus("scheduled");
                teamService.save(home);
                teamService.save(away);
                gameService.save(game);
            }
        }
    }

    /**
     * method sends messages to users informing them about incoming game of the teams the user is subscribed
     * @param team1 team the user is subscribed to
     * @param team2 team the user's team is playing against
     * @param startDate match start date
     */
    public void notifySubscribers(Team team1, Team team2, Timestamp startDate){
        List<User> teamSubscribers = team1.getSubscribers();
        for (User u : teamSubscribers) {
            pl.coderslab.bets.entity.Message message = new pl.coderslab.bets.entity.Message();
            message.setSender("SYSTEM");
            message.setRecipient(u);
            message.setTitle("Incoming game notification");
            message.setText("your team " + team1.getName() +
                    " has a match against "+ team2.getName() + " scheduled for " + startDate);
            messageService.saveSimpleMessage(message);
        }
        //trying to implement sending messages to topic - JMS
            messageService.publishMessage("newGame", "your team " + team1.getName() +
                    " has a match against "+ team2.getName() + " scheduled for " + startDate);
    }

    /**
     * this method is used to set game odds, the odds are random generated values
     * @return random generated double - odd (value between 1 and 5)
     */
    public double setOdd(){
        Faker faker = new Faker();
        return faker.number().randomDouble(2, 1, 5);
    }

    /**
     * Method gameStatusCheck queries the database on a fixed time base of 1 second, searching for games
     * starting or finishing.
     * Each game that has start time that is past current time and game status is "scheduled",
     * has its status switched to "live".
     * Each game that has end time that is past current time and game status is "live",
     * has its status switched to "finished".
     * Each finished game then has its final results set.
     * The goals scored and lost for each team are added up, the winner of the game is chosen.
     * If the amount of the goals scored by both teams is equal, the result of the game is set as draw.
     * In that case, the game winner is set to 'null'.
     * After match summary, the teams have their "in-game" flag set to false,
     * and are therefore free to be chosen for other games
     * The results of all actions are saved in the database.
     */
    @Scheduled(cron = "1/1 * * * * ?")
    public void gameStatusCheck() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        List<Game> goingLive = gameService.findGamesStarting(timestamp,"scheduled");
        for (Game g : goingLive) {
            g.setStatus("live");
            gameService.save(g);
        }

        List<Game> ending = gameService.findGamesEnding(timestamp, "live");

        for (Game g : ending) {
            Team homeTeam = g.getHomeTeam();
            Team awayTeam = g.getAwayTeam();

            g.setStatus("finished");
            if (g.getAwayTeamScore() > g.getHomeTeamScore()) {
                g.setWinner(awayTeam);
                awayTeam.setGamesWon(awayTeam.getGamesWon() + 1);
                homeTeam.setGoalsLost(homeTeam.getGamesLost() + 1);
            } else if (g.getAwayTeamScore() < g.getHomeTeamScore()) {
                g.setWinner(homeTeam);
                awayTeam.setGamesLost(awayTeam.getGamesLost() + 1);
                homeTeam.setGamesWon(homeTeam.getGamesWon() + 1);
            } else {
                g.setWinner(null);
                g.setDrawn(true);
                awayTeam.setDraws(awayTeam.getDraws() + 1);
                homeTeam.setDraws(homeTeam.getDraws() + 1);
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

    /**
     * The resultRigger method adds goals by random to each team that is in a live game.
     * The method works on a scheduled basis and is called every 30 seconds.
     * The list of live games is aquired from the database.
     * Then the score of each team has 0 to 1 goals added to their score by random.
     * The results are saved in the database
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resultRigger() {
        List<Game> liveGames = gameService.findGamesByStatus("live");
        Random random = new Random();
        for (Game g : liveGames) {
            g.setAwayTeamScore(g.getAwayTeamScore() + random.nextInt(2));
            g.setHomeTeamScore(g.getAwayTeamScore() + random.nextInt(2));

            gameService.save(g);
        }
    }

    /**
     * The gamesPayout method is responsible for settling the results of the bets based on the game results.
     * Method is called on a scheduled basis of 1 second.
     * First, the database is queried for games, which are finished (status - "finished"),
     * but not paid out (paidOut flag on 'false').
     * Then for for each game in the list, a list of the game's bets is acquired from the database.
     * Then, each bet in the list is checked, and paid out depending on the game results,
     * using payoutSingle bet method.
     * The results of operations are then saved in the database
     */
    @Scheduled(cron = "1/1 * * * * ?")
    public void gamesPayout() {
        List<Game> finishedGames = gameService.findFinishedGamesNotPaidOut("finished");

        for (Game g : finishedGames) {
            List<Bet> bets = betService.findAllByGameId(g.getId());
            Team winner = g.getWinner();
            Boolean draw = g.isDrawn();
            double rate = 0;
            for (Bet b : bets) {
                if (b.isWillDraw() && draw == true && winner == null) {
                    //there was a draw
                    rate = g.getDrawOdd();
                    payoutSingleBet(b, rate);
                } else if ( b.isWillDraw() && winner.equals(b.getBettingTeam())) {
                    if (winner.equals(g.getHomeTeam())){
                        rate = g.getHomeTeamWinOrDrawOdd();
                    }else {
                        rate = g.getAwayTeamWinOrDrawOdd();
                    }
                    payoutSingleBet(b, rate);
                } else if (winner != null  && winner.equals(b.getBettingTeam())
                        && draw == false && b.isWillDraw() == false) {
                    //betting team won
                    if (winner.equals(g.getHomeTeam())){
                        rate = g.getHomeTeamWinOdd();
                    }else {
                        rate = g.getAwayTeamWinOdd();
                    }
                    payoutSingleBet(b, rate);
                } else {
                    //betting team neither won nor drew
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

    /**
     * The method queries the database for the list of teams every two minutes and sorts the list
     * based on the teams scored goals. The teams in the sorted list then have their table standing set.
     * The results of operations are saved in database.
     */
    @Scheduled(cron = "* 0/2 * * * ?")
    public void StandingsCheck() {
        List<Team> teams = teamService.findAllTeams();
        teams.sort((t1, t2) -> t1.getGoalsScored() - t2.getGoalsScored());
        int counter = 1;
        for (Team t : teams) {
            t.setTableStanding(counter);
            counter++;
            teamService.save(t);
        }
    }

    /**
     *
     * @param b - bet to be paid out
     * @param rate - bet rate, acquired from game data
     *
     * Method calculates the amount won by the user placing the bet, based on the bet amount and bet rate.
     * The 'win' flag of the bet is set to true. The amount calculated is added to user wallet.
     * The results of operation are saved in the database
     */
    public void payoutSingleBet(Bet b, double rate) {
        b.setWin(true);
        BigDecimal amount = b.getAmount();
        BigDecimal amountWon = amount.multiply(BigDecimal.valueOf(rate));
        b.setAmountWon(amountWon);
        User user = b.getUser();
        BigDecimal current = user.getWallet();
        user.setWallet(current.add(amountWon));
        b.setPaidOut(true);
        userService.save(user);
        betService.save(b);
    }

}
