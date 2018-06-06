package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TeamRepository teamRepository;


    @Test
    public void findAllOrderByTableStandingDesc() {
        //given
        Team team = new Team();
        team.setName("test");
        entityManager.persist(team);
        //when
        List<Team> result = teamRepository.findAllOrderByTableStandingDesc();
        List<Team> expected = new ArrayList<>();
        expected.add(team);
        //then
        assertEquals(result, expected);
    }

    @Test
    public void findAllByInGameFalse() {
        //given
        Team team = new Team();
        team.setName("test");
        team.setInGame(false);
        entityManager.persist(team);
        //when
        List<Team> result = teamRepository.findAllByInGameFalse();
        List<Team> expected = new ArrayList<>();
        expected.add(team);
        //then
        assertEquals(result, expected);
    }
}