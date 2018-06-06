package pl.coderslab.bets.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.repository.LeagueRepository;
import pl.coderslab.bets.serviceImpl.LeagueServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeagueServiceTest {

    private LeagueService leagueService;
    @Autowired
    private LeagueRepository leagueRepository;

    @Before
    public void setUp() {
        leagueService = new LeagueServiceImpl();
        leagueRepository = mock(LeagueRepository.class);
    }
    
    @Test
    public void save() {
        // given
        League league = new League();
        league.setId(1);
        List<League> leagues = new ArrayList<>();
        leagues.add(league);

        // when
        when(leagueRepository.save(league)).thenReturn(league);
        List<League> leagues2 = new ArrayList<>();
        leagues2.add(league);
        // then
        assertEquals(leagues, leagues2);
    }

    @Test
    public void findLeagueByName() {
        // given
        League league = new League();
        league.setName("test");

        // when
        when(leagueRepository.findByNameEquals("test")).thenReturn(league);
        // then
        assertEquals("test", league.getName());
    }

    @Test
    public void findAllLeagues() {
// given
        League league = new League();
        List<League> leagues = new ArrayList<>();
        leagues.add(league);

        // when
        when(leagueRepository.findAll()).thenReturn(leagues);
        List<League> leagues2 = new ArrayList<>();
        leagues2.add(league);
        // then
        assertEquals(leagues, leagues2);
    }
}