package pl.coderslab.bets.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.repository.UserRepository;
import pl.coderslab.bets.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;

@Transactional
@Controller
public class LoginController {

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
//        if(result.hasErrors()){
//            return "register";
//        }
        user.setWallet(BigDecimal.valueOf(10));

//        user.setCryptSalt(BCrypt.gensalt());
//        user.setPassword(BCrypt.hashpw(user.getPassword(), user.getCryptSalt()));
//        userRepository.save(user);
      userService.saveUser(user);
        return "redirect:/";
    }

//    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
//    public ModelAndView registerUserAccount
//            (@ModelAttribute("user") @Valid UserDto accountDto,
//             BindingResult result, WebRequest request, Errors errors) {
//        User registered = new User();
//        if (!result.hasErrors()) {
//            registered = createUserAccount(accountDto, result);
//        }
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        // rest of the implementation
//    }
//    private User createUserAccount(UserDto accountDto, BindingResult result) {
//        User registered = null;
//        try {
//            registered = service.registerNewUserAccount(accountDto);
//        } catch (EmailExistsException e) {
//            return null;
//        }
//        return registered;
//    }


//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }
}
