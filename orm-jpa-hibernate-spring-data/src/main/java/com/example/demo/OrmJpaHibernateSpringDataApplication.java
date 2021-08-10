package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entities.Student;

@SpringBootApplication
public class OrmJpaHibernateSpringDataApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OrmJpaHibernateSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.save(new Student(null, "Thierry", "thierry@gmail.com", new Date(), 45));
		studentRepository.save(new Student(null, "Nambinina", "nambinina@gmail.com", new Date(), 36));
		studentRepository.save(new Student(null, "Ninah", "ninah@gmail.com", new Date(), 41));
		studentRepository.save(new Student(null, "Hasimino", "hasimino@gmail.com", new Date(), 25));
		studentRepository.findAll().forEach(student ->{
			System.out.println(student.toString());
		});
		
		Student student = studentRepository.findById(1L).get();
		System.out.println(student.toString());
	}

}
