package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.bets.repository.GameRepository;
import pl.coderslab.bets.repository.TeamRepository;
import pl.coderslab.bets.repository.UserRepository;

@Controller
public class MenuController {

    @Autowired
    GameRepository gameRepository;


    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;


    //if method works add tables and controllers for different sports
    @GetMapping("/menu/view")
    public String viewGames(Model model){
        model.addAttribute("scheduledGames", gameRepository.findAllByStatusEquals("scheduled"));
        return "games";
    }

    @GetMapping("/menu/live")
    public String viewLiveGames(Model model){
        model.addAttribute("liveGames", gameRepository.findAllByStatusEquals("live"));
        return "live";
    }

    @GetMapping("/menu/results")
    public String viewResults(Model model){
        model.addAttribute("results", gameRepository.findAllByStatusEquals("finished"));
        return "results";
    }

    @GetMapping("/menu/standings")
    public String viewStandings(Model model){
        model.addAttribute("standings", teamRepository.findAllOrderByTableStandingDesc());
        return "standings";
    }
//<a th:href="@{/game/bet(id=${game.id})}">bet</a>
    //maybe get current user from session
//    @GetMapping("/menu/viewbets(id=${user.id})")
//    public String viewUsersCurrentBets(HttpSession session, Model model, @RequestParam long id){
////        session.
//        model.addAttribute("user", userRepository.findById(id));
//        return "userBets";
//    }
//    //maybe get current user from session
//    @GetMapping("/menu/viewaccount(id=${user.id})")
//    public String viewUserAccount(Model model, @RequestParam long id){
//        model.addAttribute("user", userRepository.findById(id));
//        return "account";
//    }

}
