package com.example.web_jee_spring_mvc_security.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_role")
@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter @ToString
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String role;
}
