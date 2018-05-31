package pl.coderslab.bets.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.bets.entity.User;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

}
