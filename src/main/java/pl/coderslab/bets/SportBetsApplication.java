package pl.coderslab.bets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ServletComponentScan
@SpringBootApplication
public class SportBetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportBetsApplication.class, args);
    }
}
