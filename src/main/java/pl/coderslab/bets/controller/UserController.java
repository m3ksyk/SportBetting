package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

//        user.setCryptSalt(BCrypt.gensalt());
//        user.setPassword(BCrypt.hashpw(user.getPassword(), user.getCryptSalt()));
//        userRepository.save(user);
        userService.saveUser(user);
        return "redirect:/";
    }
}
