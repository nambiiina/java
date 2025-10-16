package com.example.web_jee_spring_mvc_security.repository;

import com.example.web_jee_spring_mvc_security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByRole(String role);
}
