package com.example.demo;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entities.Student;

@SpringBootApplication
public class OrmJpaHibernateSpringDataApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmJpaHibernateSpringDataApplication.class, args);
		StudentRepository studentRepository = context.getBean(StudentRepository.class);
		studentRepository.save(new Student(null, "Thierry", "thierry@gmail.com", new Date(), 45));
		studentRepository.save(new Student(null, "Nambinina", "nambinina@gmail.com", new Date(), 36));
		studentRepository.save(new Student(null, "Ninah", "ninah@gmail.com", new Date(), 41));
		studentRepository.save(new Student(null, "Hasimino", "hasimino@gmail.com", new Date(), 25));
		studentRepository.findAll().forEach(student ->{
			System.out.println(student.toString());
		});
	}

}
