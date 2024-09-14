package com.springRest.configuration;

import java.util.ArrayList;
import java.util.List;

import com.springRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.springRest.entity.User user = userService.findByEmail(username);

        return new User(user.getEmail(), user.getPassword(), getGrantedAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}