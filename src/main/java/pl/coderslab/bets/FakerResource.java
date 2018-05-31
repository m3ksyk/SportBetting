package pl.coderslab.bets;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class FakerResource {

//    @Autowired
//    FakerService fakerService;
//

//    @GetMapping(path= "/fake-today-games")
//    public String todayGames() {
//        return fakerService.getTodayGames().toString();
//    }
//
//    @GetMapping(path= "/fake-current-games")
//    public String currentGames() {
//        return fakerService.getCurrentGames().toString();
//    }
//
//    @GetMapping(path= "/fake-leagues")
//    public String leagues() {
//        return fakerService.getLeagues().toString();
//    }
//
//    @GetMapping(path= "/fake-countries")
//    public String countries() {
//
//        return fakerService.getCountries().toString();
//    }
//
//    @GetMapping(path= "/get-sports")
//    public String sportsList() {
//
//        return fakerService.getSports().toString();
//    }
//    @GetMapping(path= "/get-users")
//    public String usersList() {
//
//        return fakerService.getUsers().toString();
//    }
}