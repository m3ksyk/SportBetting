package pl.coderslab.bets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.bets.service.scheduled.ScheduledTasksService;

@Controller
public class GameController {

    @Autowired
    ScheduledTasksService scheduledTasksService;

    @GetMapping("/populate")
    @ResponseBody
    public String populateDB(){
        scheduledTasksService.createSports();
        scheduledTasksService.createLeagues();
        scheduledTasksService.createTeams();
        return "DONE";
    }

//    @GetMapping("/game/placeBet")
//    public String placeBet(){
//        return null;
//    }
}
