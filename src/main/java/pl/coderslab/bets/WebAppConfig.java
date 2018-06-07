package pl.coderslab.bets;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * API web configuration class
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/404").setViewName("404");
        registry.addViewController("/500").setViewName("500");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");
    }
}