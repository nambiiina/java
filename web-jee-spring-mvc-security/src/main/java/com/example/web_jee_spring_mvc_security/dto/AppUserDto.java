package com.example.web_jee_spring_mvc_security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUserDto {
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters")
    private String rawPassword;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    private Boolean enabled = true;

    private String confirmPassword;

    private Set<String> roles;   // code des rôles : ADMIN, USER, ...
}
