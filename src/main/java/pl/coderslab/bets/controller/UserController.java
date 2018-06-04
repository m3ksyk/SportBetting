package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String recharge(WebRequest request, Model model){
        //get current user add to model
//        User user = (User) request.getUserPrincipal();
//        model.addAttribute("user", user);
        return "recharge";
    }

    @PostMapping("/user/recharge")
    public String recharge(@ModelAttribute User user, @ModelAttribute BigDecimal amount, BindingResult result){
        if(result.hasErrors()){
            return "recharge";
        }

        BigDecimal current = user.getWallet();
        BigDecimal res = current.add(amount);
        user.setWallet(res);

        userService.saveUser(user);
        return "recharge";
    }
}
