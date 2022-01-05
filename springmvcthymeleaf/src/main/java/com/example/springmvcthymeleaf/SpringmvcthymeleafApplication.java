package com.example.springmvcthymeleaf;

import com.example.springmvcthymeleaf.dao.PatientRepository;
import com.example.springmvcthymeleaf.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SpringBootApplication
public class SpringmvcthymeleafApplication {

    private final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    private final Random rand = new Random();
    private final Set<String> identifiers = new HashSet<String>();

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcthymeleafApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(PatientRepository patientRepository) throws Exception {
        return (String[] args) -> {
            patientRepository.save(new Patient(null, randomIdentifier(), new Date(), false, new Random().nextInt(100-4) + 4));
            patientRepository.save(new Patient(null, randomIdentifier(), new Date(), true, new Random().nextInt(100-4) + 4));
            patientRepository.save(new Patient(null, randomIdentifier(), new Date(), false, new Random().nextInt(100-4) + 4));
//            patientRepository.findAll().forEach(p -> System.out.println(p.getName()));
        };
    }

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
