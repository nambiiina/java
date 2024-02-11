package org.sid.springsecurity.service;

import lombok.AllArgsConstructor;
import org.sid.springsecurity.entity.AppRole;
import org.sid.springsecurity.entity.AppUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = Optional.ofNullable(accountService.loadUserByUsername(username));
        if (appUser.isEmpty()) throw new UsernameNotFoundException(String.format("User %s not found", username));
        /*return User
                .withUsername(appUser.get().getUsername())
                .password(appUser.get().getPassword())
                .roles(appUser.get().getAppRoles().stream().map(AppRole::getRoleName).toArray(String[]::new)).build();*/

        // Use authorities instead roles ROLE_USER => USER and hasAuthority to check
        List<SimpleGrantedAuthority> authorities = appUser.get().getAppRoles().stream().map(appRole -> new SimpleGrantedAuthority(appRole.getRoleName())).toList();
        return User
                .withUsername(appUser.get().getUsername())
                .password(appUser.get().getPassword())
                .authorities(authorities).build();
    }
}
