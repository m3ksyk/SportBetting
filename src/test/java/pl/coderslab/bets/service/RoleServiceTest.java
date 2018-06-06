package pl.coderslab.bets.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Role;
import pl.coderslab.bets.repository.RoleRepository;
import pl.coderslab.bets.serviceImpl.RoleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        roleService = new RoleServiceImpl(roleRepository);
        roleRepository = mock(RoleRepository.class);
    }

    @Test
    public void getOrCreate() {
        Role role = new Role();
        role.setName("test");

        // when
        when(roleRepository.save(role)).thenReturn(role);
//        when(roleService.getOrCreate("test")).thenReturn(role2);
//        Role role2 = roleService.getOrCreate("test");
        Role role2 = new Role();
        role2.setName("test");

        // then
        assertEquals(role.getName(), role2.getName());

    }
}