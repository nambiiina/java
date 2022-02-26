package com.example.springbootjwt.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "firstNameTraining", types = {Student.class})
public interface StudentFirstNameTrainingProjection {
    String getFirstName();
    Training getTraining();
}