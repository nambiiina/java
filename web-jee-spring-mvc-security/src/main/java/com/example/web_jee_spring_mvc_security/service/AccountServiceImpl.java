package com.example.web_jee_spring_mvc_security.service;

import com.example.web_jee_spring_mvc_security.AccountService;
import com.example.web_jee_spring_mvc_security.dto.AppUserDto;
import com.example.web_jee_spring_mvc_security.entities.AppRole;
import com.example.web_jee_spring_mvc_security.entities.AppUser;
import com.example.web_jee_spring_mvc_security.exception.PasswordMismatchException;
import com.example.web_jee_spring_mvc_security.exception.RoleNotFoundException;
import com.example.web_jee_spring_mvc_security.exception.UsernameExistsException;
import com.example.web_jee_spring_mvc_security.repository.AppRoleRepository;
import com.example.web_jee_spring_mvc_security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(AppUserDto appUserDto) throws UsernameExistsException, PasswordMismatchException {
        if (!appUserDto.getRawPassword().equals(appUserDto.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }
        if (appUserRepository.existsByUsername(appUserDto.getUsername())) {
            throw new UsernameExistsException(appUserDto.getUsername());
        }
        AppUser appUser = AppUser.builder()
                .username(appUserDto.getUsername())
                .password(passwordEncoder.encode(appUserDto.getRawPassword()))
                .email(appUserDto.getEmail())
                .enabled(true)
                .build();
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(String role) {
        return appRoleRepository.findByRole(role)
                .orElseGet(() ->
                        appRoleRepository.save(AppRole.builder().role(role).build())
                );
    }

    @Override
    public void addRoleToUser(String username, String role) throws RoleNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        AppRole appRole = appRoleRepository.findByRole(role)
                .orElseThrow(() -> new RoleNotFoundException(role));
        appUser.getRoles().add(appRole);
        appUserRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String role) throws RoleNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        AppRole appRole = appRoleRepository.findByRole(role)
                .orElseThrow(() -> new RoleNotFoundException(role));
        appUser.getRoles().remove(appRole);
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
