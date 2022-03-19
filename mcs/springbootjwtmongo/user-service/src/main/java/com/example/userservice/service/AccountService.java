package com.example.userservice.service;

import com.example.userservice.entities.AppRole;
import com.example.userservice.entities.AppUser;

public interface AccountService {
    AppUser saveUser(String username, String password, String confirmedPassword);
    AppRole saveRole(AppRole role);
    AppUser loadUserByUsername(String username);
    void addRoleToUser(String username, String rolename);
}
