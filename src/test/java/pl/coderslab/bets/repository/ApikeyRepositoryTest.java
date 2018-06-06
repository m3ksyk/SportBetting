package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.Apikey;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApikeyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ApikeyRepository apikeyRepository;

    @Test
    public void ShouldReturnValue() {
        //given
        Apikey ak = new Apikey();
        ak.setValue("test");
        entityManager.persist(ak);
        //when
        Apikey result = apikeyRepository.findByValueEquals("test");
        //then
        assertEquals(result.getValue(), ak.getValue());
    }


    @Test
    public void ShouldReturnNull() {
        //given
        Apikey ak = new Apikey();
        ak.setValue("test");
        entityManager.persist(ak);
        //when
        Apikey result = apikeyRepository.findByValueEquals("not-test");
        //then
        assertNull(result);
    }
}