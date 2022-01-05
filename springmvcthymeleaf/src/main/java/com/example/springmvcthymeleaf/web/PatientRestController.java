package com.example.springmvcthymeleaf.web;

import com.example.springmvcthymeleaf.dao.PatientRepository;
import com.example.springmvcthymeleaf.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {

    @Autowired
    private PatientRepository patientRepository;

    /**
     *
     * @return list of patient in json
     */
    @GetMapping("/listPatients")
    public List<Patient> list() {
        return patientRepository.findAll();
    }

    /**
     *
     * @param id
     * @return patient
     */
    @GetMapping("/patients/{id}")
    public Patient getOne(@PathVariable("id") Long id) {
        return patientRepository.findById(id).get();
    }

}
