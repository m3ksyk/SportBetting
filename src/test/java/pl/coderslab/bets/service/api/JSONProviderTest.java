package pl.coderslab.bets.service.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.repository.GameRepository;
import pl.coderslab.bets.repository.LeagueRepository;
import pl.coderslab.bets.repository.SportRepository;
import pl.coderslab.bets.repository.TeamRepository;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.LeagueService;
import pl.coderslab.bets.service.SportService;
import pl.coderslab.bets.service.TeamService;
import pl.coderslab.bets.serviceImpl.GameServiceImpl;
import pl.coderslab.bets.serviceImpl.LeagueServiceImpl;
import pl.coderslab.bets.serviceImpl.SportServiceImpl;
import pl.coderslab.bets.serviceImpl.TeamServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JSONProviderTest {

    private TeamService teamService;
    private GameService gameService;
    private SportService sportService;
    private LeagueService leagueService;

    @Mock
    private TeamRepository teamRepository;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private SportRepository sportRepository;
    @Mock
    private LeagueRepository leagueRepository;

    @Before
    public void setUp() {
        teamService = new TeamServiceImpl();
        gameService = new GameServiceImpl();
        sportService = new SportServiceImpl();
        leagueService = new LeagueServiceImpl();
    }
    @Test
    public void getLiveGames() throws JSONException {
    //given
        JSONObject liveGamesJSON = new JSONObject();
        JSONObject liveGamesJSON2 = new JSONObject();
        final String status = "live";
        List<Game> liveGames = new ArrayList<>();
        when(gameRepository.findAllByStatusEquals(status)).thenReturn(liveGames);
        liveGamesJSON.put("liveGames", liveGames);
    // when
        liveGamesJSON2.put("liveGames", liveGames);
    // then
        assertEquals(liveGamesJSON.get("liveGames"), liveGamesJSON2.get("liveGames"));
    }

    @Test
    public void getGamesResults() throws JSONException {
        //given
        JSONObject finishedGamesJSON = new JSONObject();
        JSONObject finishedGamesJSON2 = new JSONObject();
        final String status = "finished";
        List<Game> finishedGames = new ArrayList<>();
        when(gameRepository.findAllByStatusEquals(status)).thenReturn(finishedGames);
        finishedGamesJSON.put("finishedGames", finishedGames);
        // when
        finishedGamesJSON2.put("finishedGames", finishedGames);
        // then
        assertEquals(finishedGamesJSON.get("finishedGames"), finishedGamesJSON2.get("finishedGames"));
    }

    @Test
    public void getScheduledGames() throws JSONException {
        //given
        JSONObject scheduledGamesJSON = new JSONObject();
        JSONObject scheduledGamesJSON2 = new JSONObject();
        final String status = "scheduled";
        List<Game> scheduledGames = new ArrayList<>();
        when(gameRepository.findAllByStatusEquals(status)).thenReturn(scheduledGames);
        scheduledGamesJSON.put("scheduledGames", scheduledGames);
        // when
        scheduledGamesJSON2.put("scheduledGames", scheduledGames);
        // then
        assertEquals(scheduledGamesJSON.get("scheduledGames"), scheduledGamesJSON2.get("scheduledGames"));
    }

    @Test
    public void getLeagues() throws JSONException {
        //given
        JSONObject leaguesJSON= new JSONObject();
        JSONObject leaguesJSON2= new JSONObject();
        List<League> leagues = new ArrayList<>();
        when(leagueRepository.findAll()).thenReturn(leagues);
        leaguesJSON.put("leagues", leagues);
        // when
        leaguesJSON2.put("leagues", leagues);
        // then
        assertEquals(leaguesJSON.get("leagues"), leaguesJSON2.get("leagues"));
    }

    @Test
    public void getSports() throws JSONException {
        //given
        JSONObject sportsJSON = new JSONObject();
        JSONObject sportsJSON2 = new JSONObject();
        List<Sport> sports = new ArrayList<>();
        when(sportRepository.findAll()).thenReturn(sports);
        sportsJSON.put("sports", sports);
        // when
        sportsJSON2.put("sports", sports);
        // then
        assertEquals(sportsJSON.get("sports"), sportsJSON2.get("sports"));
    }

    @Test
    public void getTeams() throws JSONException {
        //given
        JSONObject teamsJSON = new JSONObject();
        JSONObject teamsJSON2 = new JSONObject();
        List<Team> teams = new ArrayList<>();
        when(teamRepository.findAll()).thenReturn(teams);
        teamsJSON.put("teams", teams);
        // when
        teamsJSON2.put("teams", teams);
        // then
        assertEquals(teamsJSON.get("teams"), teamsJSON2.get("teams"));
    }

}