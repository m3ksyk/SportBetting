package pl.coderslab.bets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.coderslab.bets.serviceImpl.UserDetailsServiceImpl;

import javax.validation.Validator;

/**
 * api security configuration
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(WebSecurity web) {
        // web.debug(true);
//        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()

                .antMatchers("/", "/webjars/**", "/login", "/api/*").permitAll()
                .antMatchers("/populate").denyAll()
                .antMatchers("/register").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index").permitAll()
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new
                DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }


}