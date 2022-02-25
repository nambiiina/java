package com.example.springbootjwt;

import com.example.springbootjwt.entities.Student;
import com.example.springbootjwt.entities.Training;
import com.example.springbootjwt.repository.StudentRepository;
import com.example.springbootjwt.repository.TrainingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class SpringbootjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjwtApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository, TrainingRepository trainingRepository) {
        return args -> {
            Training js = trainingRepository.save(new Training(null, "Javascript", Duration.ofHours(2L), null));
            Training java = trainingRepository.save(new Training(null, "Java", Duration.ofHours(2L), null));
            Training sql = trainingRepository.save(new Training(null, "Sql", Duration.ofHours(2L), null));


            studentRepository.save(new Student(null, "Randria", "Thierry", LocalDate.now(), js));
            studentRepository.save(new Student(null, "Ramaro", "Nambinina", LocalDate.now(), java));
            studentRepository.save(new Student(null, "Razafi", "Ninah", LocalDate.now(), js));
            studentRepository.save(new Student(null, "Raoli", "Hasimino", LocalDate.now(), sql));
            studentRepository.save(new Student(null, "Rakoto", "nambi", LocalDate.now(), java));
            studentRepository.save(new Student(null, "Ravao", "mimi", LocalDate.now(), js));

              /*studentRepository.findAll().forEach(student -> {
                  System.out.println(student);
              });*/
        };
    }

}
