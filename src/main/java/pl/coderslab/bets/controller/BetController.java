package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.service.BetService;
import pl.coderslab.bets.service.GameService;
import pl.coderslab.bets.service.UserService;

import java.math.BigDecimal;

@Controller
public class BetController {
    @Autowired
    GameService gameService;

    @Autowired
    BetService betService;

    @Autowired
    UserService userService;

    @GetMapping("/game/{id}/bet")
    public String placeBet(@PathVariable("id") Long id, Model model, WebRequest request) {
        Game game = gameService.findById(id);
        String username = request.getUserPrincipal().getName();
        User user = userService.findByUsername(username);

        double amount = 0;
        int choice = 0;
        model.addAttribute("amount", amount);
        model.addAttribute("choice", choice);
        model.addAttribute("user", user);
        model.addAttribute("id", game.getId());
        model.addAttribute("game", game);
        return "placeBet";
    }

    @PostMapping("/game/bet")
    public String placeBet( WebRequest request, @RequestParam("amount") double amount,
                            @RequestParam("id") Long id,
                            @RequestParam("choice") int choice, Model model){
        Game game = gameService.findById(id);

        String name =request.getUserPrincipal().getName();
        User user = userService.findByUsername(name);

        BigDecimal wallet = user.getWallet();

        if (wallet.compareTo(BigDecimal.valueOf(amount)) < 0){
            return "placeBet";
            //dodac komunikaty błędu, że za mało na koncie, zalecic doładowanie
        }

        Bet bet = new Bet();
        bet.setUser(user);
        bet.setAmount(BigDecimal.valueOf(amount));
        bet.setGame(game);
        switch (choice){
            case 1 : bet.setBettingTeam(game.getHomeTeam());
                    bet.setWillDraw(false);
                    bet.setRate(BigDecimal.valueOf(game.getHomeTeamWinOdd()));
                    break;
            case 2 : bet.setBettingTeam(game.getHomeTeam());
                     bet.setWillDraw(true);
                     bet.setRate(BigDecimal.valueOf(game.getHomeTeamWinOrDrawOdd()));
                     break;
            case 3 : bet.setBettingTeam(null);
                     bet.setWillDraw(true);
                     bet.setRate(BigDecimal.valueOf(game.getDrawOdd()));
                     break;
            case 4 : bet.setBettingTeam(game.getAwayTeam());
                     bet.setWillDraw(true);
                     bet.setRate(BigDecimal.valueOf(game.getAwayTeamWinOrDrawOdd()));
                     break;
            case 5 : bet.setBettingTeam(game.getAwayTeam());
                     bet.setWillDraw(false);
                     bet.setRate(BigDecimal.valueOf(game.getAwayTeamWinOdd()));
                     break;
        }

        user.setWallet(user.getWallet().subtract(BigDecimal.valueOf(amount)));
        gameService.save(game);
        userService.save(user);
        model.addAttribute("user", user);
        model.addAttribute("id", user.getId());
        betService.save(bet);
        return "redirect:/index";
    }

}
