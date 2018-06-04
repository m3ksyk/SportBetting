package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.service.BetService;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class BetController {
    @Autowired
    GameService gameService;

    @Autowired
    BetService betService;

    @Autowired
    UserService userService;

    //sypie 500!!
    @GetMapping("/game/{id}/bet")
    public String placeBet(@PathVariable("id") Long id, Model model, WebRequest request) {
        Bet bet = new Bet();
        Game game = gameService.findById(id);
            //get current user add to model
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);
        bet.setUser(user);
        bet.setGame(game);
        model.addAttribute("user", user);
        model.addAttribute("game", game);
        model.addAttribute("bet", bet);
        gameService.save(game);
        return "placeBet";
    }

    @PostMapping("/game/{id}/bet")
    public String placeBet(@Valid @ModelAttribute Bet bet, BindingResult result) {
        if(result.hasErrors()){
            return "placeBet";
        }

        betService.save(bet);
        return "redirect:/index";
    }
}
