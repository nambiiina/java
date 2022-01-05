package com.example.springmvcthymeleaf.web;

import com.example.springmvcthymeleaf.dao.PatientRepository;
import com.example.springmvcthymeleaf.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/patients")
    public String listPatients(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        //Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(page, size));
        Page<Patient> patientPage = patientRepository.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping(path = "/deletePatient")
    public String delete(Long id, String keyword, int page, int size) {
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
    }

    @GetMapping(path = "/addPatient")
    public String create(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("mode", "new");
        return "addPatient";
    }

    @PostMapping(path = "/savePatient")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "addPatient";
        patientRepository.save(patient);
        model.addAttribute("patient", patient);
        return "confirmation";
    }

    @GetMapping(path = "/editPatient")
    public String editPatient(Model model, Long id) {
        Patient p = patientRepository.findById(id).get();
        model.addAttribute("patient", p);
        model.addAttribute("mode", "edit");
        return"addPatient";
    }

    /*
    *//**
     *
     * @return list of patient in json
     *//*
    @GetMapping("/listPatients")
    @ResponseBody
    public List<Patient> list() {
        return patientRepository.findAll();
    }

    *//**
     *
     * @param id
     * @return patient
     *//*
    @GetMapping("/patients/{id}")
    @ResponseBody
    public Patient getOne(@PathVariable("id") Long id) {
        return patientRepository.findById(id).get();
    }
    */

}
