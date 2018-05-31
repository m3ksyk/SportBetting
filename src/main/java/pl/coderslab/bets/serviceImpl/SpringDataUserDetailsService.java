package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.service.UserDetailsService;
import pl.coderslab.bets.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {
    private UserService userService;
    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(

                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }

}

