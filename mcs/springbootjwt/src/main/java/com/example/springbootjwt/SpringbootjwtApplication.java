package com.example.springbootjwt;

import com.example.springbootjwt.entities.Student;
import com.example.springbootjwt.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class SpringbootjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjwtApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository) {
        return args -> {
          studentRepository.save(new Student(null, "Randria", "Thierry", LocalDate.now()));
          studentRepository.save(new Student(null, "Randria", "Nambinina", LocalDate.now()));
          studentRepository.save(new Student(null, "Razafi", "Ninah", LocalDate.now()));
          studentRepository.save(new Student(null, "Razafi", "Hasimino", LocalDate.now()));

          studentRepository.findAll().forEach(student -> {
              System.out.println(student);
          });
        };
    }

}
