package com.example.web_jee_spring_mvc_security.repository;

import com.example.web_jee_spring_mvc_security.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
