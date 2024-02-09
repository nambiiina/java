package org.sid.springsecurity.controller;

import lombok.AllArgsConstructor;
import org.sid.springsecurity.dto.RoleUserDto;
import org.sid.springsecurity.entity.AppRole;
import org.sid.springsecurity.entity.AppUser;
import org.sid.springsecurity.service.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccounController {

    private AccountService accountService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AppUser> getAllUsers() {
        return accountService.getListUsers();
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AppUser saveUser(@RequestBody AppUser newAppUser) {
        return accountService.addNewUser(newAppUser);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AppRole> getAllRoles() {
        return accountService.getListRoles();
    }

    @PostMapping("/roles")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AppRole saveRole(@RequestBody AppRole newAppRole) {
        return accountService.addNewRole(newAppRole);
    }

    @PostMapping("/addRoleToUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserDto roleUserDto) {
        accountService.addRoleToUser(roleUserDto.getUsername(), roleUserDto.getRoleName());
    }
}
