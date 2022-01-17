package com.example.servicecompany;

import com.example.servicecompany.dao.CompanyRepository;
import com.example.servicecompany.entities.Company;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ServiceCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCompanyApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompanyRepository companyRepository) {
        return args -> {
            Stream.of("Orange", "Ravinala", "Habibo").forEach(company -> {
                companyRepository.save(new Company(null, company, 100+Math.random()*900));
            });
            companyRepository.findAll().forEach(System.out::println);
        };
    }

    /*
    Enable httptrace
     */
    @Bean
    public HttpTraceRepository htttpTraceRepository()
    {
        return new InMemoryHttpTraceRepository();
    }

}
