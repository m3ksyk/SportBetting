package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.service.BetService;
import pl.coderslab.bets.service.GameService;

import javax.validation.Valid;

@Controller
public class BetController {
    @Autowired
    GameService gameService;

    @Autowired
    BetService betService;


    //sypie 500!!
    @GetMapping("/game/bet")
    public String placeBet(@RequestParam("id") Long id, Model model) {
        model.addAttribute("game", gameService.findById(id));
        model.addAttribute("bet", new Bet());
        return "placeBet";
    }

    @PostMapping("/game/bet")
    public String placeBet(@Valid @ModelAttribute Bet bet, BindingResult result) {
        if(result.hasErrors()){
            return "placeBet";
        }
        //set rate somewhere here!!
        betService.save(bet);
        return "redirect:/index";
    }
}
