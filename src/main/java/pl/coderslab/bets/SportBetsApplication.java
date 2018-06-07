package pl.coderslab.bets;

import org.h2.util.New;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import pl.coderslab.bets.service.consumer.ConsumerService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ServletComponentScan
@SpringBootApplication
public class SportBetsApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(SportBetsApplication.class, args);
        SpringApplication.run(SportBetsApplication.class, args);
//        ConsumerService cs = context.getBean(ConsumerService.class);
//        cs.start();
    }
}
