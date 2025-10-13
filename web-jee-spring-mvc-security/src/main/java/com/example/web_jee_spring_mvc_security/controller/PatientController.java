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

import java.util.Optional;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String search(Model model,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size,
                                @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> paginatedPatients = patientRepository.findByFirstNameContainsIgnoreCase(keyword, PageRequest.of(page, size));
        if (paginatedPatients.getNumberOfElements() == 0 && paginatedPatients.getTotalPages() > 0) return "redirect:/patients?page=" + --page +  "&keyword=" + keyword;
        model.addAttribute("patients", paginatedPatients.getContent());
        model.addAttribute("totalPages", new int[paginatedPatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patient/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id, @RequestParam(name = "page") Integer page, @RequestParam(name = "keyword") String keyword) {
        patientRepository.deleteById(id);
        return "redirect:/patients?page=" + page +  "&keyword=" + keyword;
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

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) return "patient/list";
        model.addAttribute("patient", patient.get());
        return "patient/edit";
    }

}
