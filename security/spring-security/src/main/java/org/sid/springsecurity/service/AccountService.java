package org.sid.springsecurity.service;

import org.sid.springsecurity.entity.AppRole;
import org.sid.springsecurity.entity.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser newUser);
    AppRole addNewRole(AppRole newRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> getListUsers();
}
