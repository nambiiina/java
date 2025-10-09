package com.example.web_jee_spring_mvc_security.controller;

import com.example.web_jee_spring_mvc_security.entities.Patient;
import com.example.web_jee_spring_mvc_security.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String listPaginated(Model model,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Patient> paginatedPatients = patientRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("patients", paginatedPatients.getContent());
        model.addAttribute("totalPages", new int[paginatedPatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "patient/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
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
        return "redirect:/patients";
    }
}
