package com.example.web_jee_spring_mvc_security.service;

import com.example.web_jee_spring_mvc_security.AccountService;
import com.example.web_jee_spring_mvc_security.entities.AppRole;
import com.example.web_jee_spring_mvc_security.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if (Optional.ofNullable(appUser).isEmpty()) throw new UsernameNotFoundException(username);
        String[] roles = appUser.getRoles().stream().map(AppRole::getRole).toArray(String[]::new);
       return User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
    }
}
