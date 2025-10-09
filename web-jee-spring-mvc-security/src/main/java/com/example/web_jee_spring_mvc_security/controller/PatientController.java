package com.example.web_jee_spring_mvc_security.controller;

import com.example.web_jee_spring_mvc_security.entities.Patient;
import com.example.web_jee_spring_mvc_security.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String list(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patient/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients/list";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(Model model) {
        model.addAttribute("patient", Patient.builder().build());
        return "patient/new";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String save(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "patient/new";
        patientRepository.save(patient);
        return "redirect:/patients/list";
    }
}
