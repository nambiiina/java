package com.etech.microservice.micro_a;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

import com.etech.microservice.micro_a.commun.ssl.SSLUtilities;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@PropertySource("classpath:param_fonctionnel.properties")
public class MicroServiceAMCSApplication implements ApplicationRunner {

	public static void main(String[] args) {

		SSLUtilities.trustAllHostnames();
		SSLUtilities.trustAllHttpsCertificates();
		SpringApplication.run(MicroServiceAMCSApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	}

}
