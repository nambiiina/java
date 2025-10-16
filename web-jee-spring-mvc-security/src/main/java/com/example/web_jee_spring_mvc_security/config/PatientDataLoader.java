package com.example.web_jee_spring_mvc_security.config;

import com.example.web_jee_spring_mvc_security.entities.AppRole;
import com.example.web_jee_spring_mvc_security.entities.AppUser;
import com.example.web_jee_spring_mvc_security.entities.Patient;
import com.example.web_jee_spring_mvc_security.repository.AppRoleRepository;
import com.example.web_jee_spring_mvc_security.repository.AppUserRepository;
import com.example.web_jee_spring_mvc_security.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

@Slf4j
//@Profile("dev")
@Component
@RequiredArgsConstructor
public class PatientDataLoader implements ApplicationRunner {

    private final PatientRepository patientRepository;
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
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
        // 1. rôles
        AppRole adminRole = appRoleRepository.findByRole("ADMIN")
                .orElseGet(() -> appRoleRepository.save(AppRole.builder().role("ADMIN").build()));

        AppRole userRole = appRoleRepository.findByRole("USER")
                .orElseGet(() -> appRoleRepository.save(AppRole.builder().role("USER").build()));

        // 2. utilisateurs
        // utilisateurs (on duplique le code métier **minimal**)
        if (!appUserRepository.existsByUsername("admin")) {
            AppUser admin = AppUser.builder()
                    .username("admin")
                    .password("admin")
                    .email("admin@mail.com")
                    .enabled(true)
                    .build();
            admin = appUserRepository.save(admin);
            admin.getRoles().addAll(Set.of(adminRole, userRole));
            appUserRepository.save(admin);
        }

        if (!appUserRepository.existsByUsername("user")) {
            AppUser user = AppUser.builder()
                    .username("user")
                    .password("user")
                    .email("user@mail.com")
                    .enabled(true)
                    .build();
            user = appUserRepository.save(user);
            user.getRoles().add(userRole);
            appUserRepository.save(user);
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
