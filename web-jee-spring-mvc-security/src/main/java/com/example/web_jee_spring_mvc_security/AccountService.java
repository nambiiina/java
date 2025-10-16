package com.example.web_jee_spring_mvc_security;

import com.example.web_jee_spring_mvc_security.dto.AppUserDto;
import com.example.web_jee_spring_mvc_security.entities.AppRole;
import com.example.web_jee_spring_mvc_security.entities.AppUser;
import com.example.web_jee_spring_mvc_security.exception.PasswordMismatchException;
import com.example.web_jee_spring_mvc_security.exception.RoleNotFoundException;
import com.example.web_jee_spring_mvc_security.exception.UsernameExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountService {
    AppUser addNewUser(AppUserDto appUserDto) throws UsernameExistsException, PasswordMismatchException;
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String role) throws RoleNotFoundException;
    void removeRoleFromUser(String username, String role) throws RoleNotFoundException;
    AppUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
