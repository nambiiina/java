package com.example.springbootjwt.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Training {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Duration duration;
    @OneToMany(mappedBy = "training")
    private Collection<Student> student;
}
