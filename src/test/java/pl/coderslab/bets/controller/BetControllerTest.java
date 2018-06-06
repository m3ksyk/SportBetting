package pl.coderslab.bets.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.bets.service.BetService;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {BetController.class})
public class BetControllerTest {

        @MockBean
        private BetService betService;

        private BetController betController;

        @Before
        public void setUp(){
            betController = new BetController();
        }

    @Test
    public void placeBet() throws Exception {
//        String viewName = betController.placeBet();
//        assertThat("page/index", is(viewName));

//        final String PAGE_NAME = "/game/{id}/bet";
//        MockMvc mockMvc = standaloneSetup(betController).build();
//        mockMvc.perform(get("/"))
//                .andExpect(view().name(PAGE_NAME))
//                .andExpect(status().isOk());
    }

    @Test
    public void placeBet1() throws Exception {
//        final String PAGE_NAME = "/game/bet";
//        MockMvc mockMvc = standaloneSetup(betController).build();
//        mockMvc.perform(get("/"))
//                .andExpect(view().name(PAGE_NAME))
//                .andExpect(status().isOk());

        //old example code
//        mockMvc.perform(get("", 1L))
//                .andExpect(status().isOk())
//                .andExpect(view().name("student/showStudent"))
//                .andExpect(model().attributeExists("student"))
//                .andExpect(model().attribute("student",
//                        hasProperty("lastName", is("Kowalski")));
//        mockMvc.perform(get("/students/list"))
//                .andExpect(model().attributeExists("list"))
//                .andExpect(model().attribute("list", hasSize(3)))
//                .andExpect(model().attribute("list",
//                        hasItem(anyOf(hasProperty("firstName"), is("Jan")))))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/html;charset=UTF-8"))
//                .andExpect(content().string(containsString("Janek")))
//                .andExpect(view().name(STUDENT_LIST_ACTION_VIEW))
//                .andDo(print());
    }
}