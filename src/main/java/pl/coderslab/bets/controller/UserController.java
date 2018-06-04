package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.repository.UserRepository;
import pl.coderslab.bets.service.UserService;

import java.math.BigDecimal;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String userCreate(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        user.setWallet(BigDecimal.valueOf(10));

        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login?error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "login.html";
    }

    @GetMapping("/user/recharge")
    public String recharge(WebRequest request, Model model, @RequestParam("id") long id){
        String userName = request.getUserPrincipal().getName();
        User user = userService.findByUsername(userName);
        User user2 = userService.findById(id);
        if (!user.equals(user2)){
            return "403";
        }
        double amount = 0;

        model.addAttribute("user", user);
        model.addAttribute("creditCardNum", user.getCreditCardNum());
        model.addAttribute("amount", amount);
        return "recharge";
    }

    @PostMapping("/user/recharge")
    public String recharge(WebRequest request, @RequestParam("amount") double amount, Model model){
        String name =request.getUserPrincipal().getName();
        User user = userService.findByUsername(name);
        BigDecimal current = user.getWallet();
        BigDecimal added = current.add(BigDecimal.valueOf(amount));
        user.setWallet(added);

        userService.save(user);
        model.addAttribute("user", user);
        model.addAttribute("id", user.getId());

        return "account";
    }
    @GetMapping("/user/messages")
    public String messages(WebRequest request, @RequestParam("id") long id, Model model){
        String userName = request.getUserPrincipal().getName();
        User user = userService.findByUsername(userName);
        User user2 = userService.findById(id);
        if (!user.equals(user2)){
            return "403";
        }
        model.addAttribute("user", user);
        model.addAttribute("id", user.getId());
        return "userMessages";
    }

}
