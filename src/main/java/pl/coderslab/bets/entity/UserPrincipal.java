package pl.coderslab.bets.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class used for security, login actions
 */

public class UserPrincipal implements UserDetails {
    private String password;
    private String username;
    private List<GrantedAuthority> authorityList;

    public UserPrincipal(User user, String encodedPassword) {
        this.username = user.getUsername();
        this.password = encodedPassword;
        this.authorityList = new ArrayList<>();

        for (Role role : user.getRoles()) {
            this.authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE;
    }
}
