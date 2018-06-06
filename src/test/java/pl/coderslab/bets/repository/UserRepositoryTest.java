package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void findByUsername() {
        //given
        User user = new User();
        user.setUsername("test");
        user.setWallet(BigDecimal.valueOf(1));
        user.setEmail("test@test.com");
        user.setPassword("password");
        entityManager.persist(user);
        //when
        User expected = userRepository.findByUsername("test");
        //then
        assertEquals(user, expected);
    }
}