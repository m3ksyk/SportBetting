package pl.coderslab.bets.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Role;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void findByName() {
        //given
        Role role= new Role();
        role.setName("test");
        entityManager.persist(role);
        //when
        Role result = roleRepository.findByName("test");
        //then
        assertEquals(result, role);
    }
}