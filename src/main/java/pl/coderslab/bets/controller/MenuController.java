package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.Team;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.service.BetService;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.TeamService;
import pl.coderslab.bets.service.UserService;

import java.util.Collections;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    GameService gameService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @Autowired
    BetService betService;

    @GetMapping("/menu/view")
    public String viewGames(Model model, WebRequest request) {
        String name = request.getUserPrincipal().getName();
        User user = userService.findByUsername(name);
        model.addAttribute("id", user.getId());
        model.addAttribute("user", user);
        model.addAttribute("scheduledGames", gameService.findGamesByStatus("scheduled"));

        return "games";
    }

    @GetMapping("/menu/live")
    public String viewLiveGames(Model model, WebRequest request) {
        String name = request.getUserPrincipal().getName();
        User user = userService.findByUsername(name);
        model.addAttribute("id", user.getId());
        model.addAttribute("user", user);
        model.addAttribute("liveGames", gameService.findGamesByStatus("live"));

        return "live";
    }

    @GetMapping("/menu/results")
    public String viewResults(Model model, WebRequest request) {
        String name = request.getUserPrincipal().getName();
        User user = userService.findByUsername(name);
        model.addAttribute("id", user.getId());
        model.addAttribute("user", user);
        model.addAttribute("gameResults", gameService.findGamesByStatus("finished"));

        return "results";
    }

    @GetMapping("/menu/standings")
    public String viewStandings(Model model, WebRequest request) {
        String name = request.getUserPrincipal().getName();
        User user = userService.findByUsername(name);
        model.addAttribute("id", user.getId());
        model.addAttribute("user", user);
        model.addAttribute("standings", teamService.findAllTeamsSortedByStanding());

        return "standings";
    }

    @GetMapping("/menu/api")
    public String viewApi(Model model, WebRequest request) {
        String name = request.getUserPrincipal().getName();
        User user = userService.findByUsername(name);
        model.addAttribute("id", user.getId());
        model.addAttribute("user", user);

        return "api";
    }

    @GetMapping("/menu/viewbets")
    public String viewUsersCurrentBets(Model model, @RequestParam("id") long id, WebRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);
        User user2 = userService.findById(id);
        if (!user.equals(user2)) {
            return "403";
        }
        List<Bet> bets = betService.findAllLiveUserBets(user);
        model.addAttribute("userBets", bets);
        model.addAttribute("user", user);

        return "userBets";
    }

    @GetMapping("/menu/viewaccount")
    public String viewUserAccount(Model model, @RequestParam("id") long id, WebRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);
        User user2 = userService.findById(id);
        if (!user.equals(user2)) {
            return "403";
        }

        List<Bet> bets = betService.findAllUserBets(id);
        model.addAttribute("userBets", bets);
        model.addAttribute("user", user);

        return "account";
    }

    @GetMapping("/menu/viewSingleGame")
    public String viewSingleGame(Model model, @RequestParam("id") long id, WebRequest request) {

        List<Game> game = Collections.singletonList(gameService.findById(id));
        model.addAttribute("singleGame", game);

        return "game";
    }

    @GetMapping("/subscribe")
    public String subscribeToTeam(Model model, @RequestParam("id") long id, WebRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);

        Team team = teamService.findTeamById(id);
        team.addSubscriber(user);
        user.addSubscription(team);
        teamService.save(team);
        userService.save(user);
        model.addAttribute("id", user.getId());
        model.addAttribute("user", user);
        return "redirect:/index";
    }

    @GetMapping("/unsubscribe")
    public String unsubscribe(Model model, @RequestParam("id") long id, WebRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);

        Team team = teamService.findTeamById(id);
        team.removeSubscriber(user);
        user.removeSubscription(team);
        teamService.save(team);
        userService.save(user);

        model.addAttribute("id", user.getId());
        model.addAttribute("user", user);
        return "redirect:/index";
    }
}