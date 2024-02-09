package org.sid.springsecurity.service;

import lombok.AllArgsConstructor;
import org.sid.springsecurity.entity.AppRole;
import org.sid.springsecurity.entity.AppUser;
import org.sid.springsecurity.repository.AppRoleRepository;
import org.sid.springsecurity.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(AppUser newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return appUserRepository.save(newUser);
    }

    @Override
    public AppRole addNewRole(AppRole newRole) {
        return appRoleRepository.save(newRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);

        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getListUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppRole> getListRoles() {
        return appRoleRepository.findAll();
    }
}
