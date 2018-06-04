package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.service.UserService;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(){
        return "login";
    }

    @GetMapping("/index")
    public String index(WebRequest request, Model model){
        String userName = request.getUserPrincipal().getName();
        User user = userService.findByUsername(userName);
        model.addAttribute("user", user);
        return "index";
    }

}
