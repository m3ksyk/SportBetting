package pl.coderslab.bets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.bets.serviceImpl.SpringDataUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
//                .withUser("test").password(encoder.encode("test123")).roles("USER");
//    }

//// my config ////

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/", "/index").permitAll()
//                .antMatchers("/register").anonymous()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .usernameParameter("login")
//                .passwordParameter(encoder.encode("password"))
//                .loginPage("/login")
//                .defaultSuccessUrl("/").permitAll()
//                .and()
//                .logout().permitAll()
//                .logoutSuccessUrl("/")
//                .and().exceptionHandling().accessDeniedPage("/403");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/game/**").hasAnyRole("USER")
                .anyRequest().permitAll()
                .and().formLogin()
                .loginPage("/login")
//                .usernameParameter("login")
//                .passwordParameter(encoder.encode("password"))
                .defaultSuccessUrl("/").permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/403");
    }

}