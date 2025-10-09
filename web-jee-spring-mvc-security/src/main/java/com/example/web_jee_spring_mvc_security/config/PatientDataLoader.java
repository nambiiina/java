package com.example.web_jee_spring_mvc_security.config;

import com.example.web_jee_spring_mvc_security.entities.Patient;
import com.example.web_jee_spring_mvc_security.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
//@Profile("dev")
@Component
@RequiredArgsConstructor
public class PatientDataLoader implements ApplicationRunner {

    private final PatientRepository patientRepository;
    private final Random rnd = new Random();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (patientRepository.count() == 0) {
            List<Patient> patients = IntStream.rangeClosed(1, 50)
                    .mapToObj(i -> Patient.builder()
                            .firstName(randomFirstName())
                            .lastName(randomLastName())
                            .dateOfBirth(randomDateOfBirth())
                            .sick(rnd.nextBoolean())
                            .score(rnd.nextInt(0, 101))
                            .build())
                    .toList();
            patientRepository.saveAll(patients);
            log.info("✅ {} fake patients inserted", patients.size());
        }
    }

    /* méthodes utilitaires */
    private String randomFirstName() {
        return List.of("Emma", "Liam", "Olivia", "Noah", "Ava", "Sophia").get(rnd.nextInt(6));
    }

    private String randomLastName() {
        return List.of("Smith", "Johnson", "Williams", "Brown", "Jones").get(rnd.nextInt(5));
    }

    private LocalDate randomDateOfBirth() {
        return LocalDate.now().minusYears(rnd.nextInt(18, 90));
    }
}
