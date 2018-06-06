package pl.coderslab.bets.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.UserService;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {IndexController.class})
public class IndexControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    GameService gameService;

    private IndexController indexController;

    @Before
    public void setUp(){
        indexController = new IndexController();
    }

    @Test
    public void home() {
        String viewName = indexController.home();
        assertThat("login", is(viewName));
    }

    @Test
    public void index() throws Exception {
//test doesnt pass because webrequest is not passed in the test
        MockMvc mockMvc = standaloneSetup(indexController).build();
        MockHttpServletRequest sr = new MockHttpServletRequest();

        WebRequest request = new ServletWebRequest(sr);
        when(request.getUserPrincipal().getName()).thenReturn("user");

        mockMvc.perform(get("/index"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attributeExists("liveGames"));

    }
}