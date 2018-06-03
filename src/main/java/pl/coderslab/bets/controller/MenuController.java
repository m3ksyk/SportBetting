package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.bets.repository.GameRepository;
import pl.coderslab.bets.repository.TeamRepository;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.TeamService;
import pl.coderslab.bets.service.UserService;

@Controller
public class MenuController {

    @Autowired
    GameService gameService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @GetMapping("/menu/view")
    public String viewGames(Model model){
        model.addAttribute("scheduledGames", gameService.findGamesByStatus("scheduled"));
        return "games";
    }

    @GetMapping("/menu/live")
    public String viewLiveGames(Model model){
        model.addAttribute("liveGames", gameService.findGamesByStatus("live"));
        return "live";
    }

    @GetMapping("/menu/results")
    public String viewResults(Model model){
        model.addAttribute("results", gameService.findGamesByStatus("finished"));
        return "results";
    }

    @GetMapping("/menu/standings")
    public String viewStandings(Model model){
        model.addAttribute("standings", teamService.findAllTeamsSortedByStanding());
        return "standings";
    }

    @GetMapping("/menu/api")
    public String viewApi(){
        return "api";
    }

//<a th:href="@{/game/bet(id=${game.id})}">bet</a>
    //maybe get current user from session
//    @GetMapping("/menu/viewbets(id=${user.id})")
//    public String viewUsersCurrentBets(HttpSession session, Model model, @RequestParam long id){
////        session.
//        model.addAttribute("user", userService.findById(id));
//        return "userBets";
//    }
//    //maybe get current user from session
//    @GetMapping("/menu/viewaccount(id=${user.id})")
//    public String viewUserAccount(Model model, @RequestParam long id){
//        model.addAttribute("user", userService.findById(id));
//        return "account";
//    }

}
