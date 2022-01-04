package com.example.springmvcthymeleaf;

import com.example.springmvcthymeleaf.dao.PatientRepository;
import com.example.springmvcthymeleaf.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringmvcthymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcthymeleafApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(PatientRepository patientRepository) throws Exception {
        return (String[] args) -> {
            patientRepository.save(new Patient(null, "Thierry", new Date(), false, 6));
            patientRepository.save(new Patient(null, "Nambinina", new Date(), false, 46));
            patientRepository.save(new Patient(null, "Ninah", new Date(), false, 35));
            patientRepository.save(new Patient(null, "Hasimino", new Date(), false, 14));
            patientRepository.findAll().forEach(p -> System.out.println(p.getName()));
        };
    }
}
