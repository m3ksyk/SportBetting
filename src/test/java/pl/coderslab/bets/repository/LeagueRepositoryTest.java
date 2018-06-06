package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.League;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeagueRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private LeagueRepository leagueRepository;


    @Test
    public void findByNameEquals() {
        //given
        League league= new League();
        league.setName("test");
        entityManager.persist(league);
        //when
        League result = leagueRepository.findByNameEquals("test");
        //then
        assertEquals(result, league);
    }
}