package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.User;
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
        model.addAttribute("gameResults", gameService.findGamesByStatus("finished"));
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

    @GetMapping("/menu/viewbets")
    public String viewUsersCurrentBets(Model model, @RequestParam("id") long id, WebRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);
        User user2 = userService.findById(id);
        if (!user.equals(user2)){
            return "403";
        }

        model.addAttribute("user", user);
        return "userBets";
    }

    @GetMapping("/menu/viewaccount")
    public String viewUserAccount(Model model, @RequestParam("id") long id, WebRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);
        User user2 = userService.findById(id);
        if (!user.equals(user2)){
            return "403";
        }

        model.addAttribute("user", user);
        return "account";
    }
}
