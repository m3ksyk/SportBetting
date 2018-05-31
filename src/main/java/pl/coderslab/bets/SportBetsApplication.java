package pl.coderslab.bets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2 //getting #1513 bug when swagger is enabled have to FIX
@SpringBootApplication
public class SportBetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportBetsApplication.class, args);
    }
}
