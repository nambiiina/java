package com.example.springmvcthymeleaf.dao;

import com.example.springmvcthymeleaf.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
