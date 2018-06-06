package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Sport;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SportRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SportRepository sportRepository;


    @Test
    public void findByNameEquals() {
        //given
        Sport sport= new Sport();
        sport.setName("test");
        entityManager.persist(sport);
        //when
        Sport result = sportRepository.findByNameEquals("test");
        //then
        assertEquals(result, sport);
    }
}