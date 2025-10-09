package com.example.web_jee_spring_mvc_security.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@ToString
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 1. Mandatory and formatted strings */
    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s-]+$", message = "First name must contain only letters, spaces or hyphens")
    @Column(nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s-]+$", message = "Last name must contain only letters, spaces or hyphens")
    @Column(nullable = false, length = 100)
    private String lastName;

    /* 2. Mandatory and coherent birth date */
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    /* 3. Sick flag: null not allowed */
    @NotNull(message = "Sick status must be specified")
    @Column(nullable = false)
    private Boolean sick = false;

    /* 4. Bounded numeric score */
    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be negative")
    @Max(value = 100, message = "Score cannot exceed 100")
    @Column(nullable = false)
    private Integer score = 0;
}
