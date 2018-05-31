package pl.coderslab.bets;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//        registry.addViewController("/logout").setViewName("logoutView");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/403").setViewName("403");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");
    }
}