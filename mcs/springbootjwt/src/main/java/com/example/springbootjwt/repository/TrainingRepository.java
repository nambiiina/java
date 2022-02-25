package com.example.springbootjwt.repository;

import com.example.springbootjwt.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
