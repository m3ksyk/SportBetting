package pl.coderslab.bets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SportBetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportBetsApplication.class, args);
    }
}
