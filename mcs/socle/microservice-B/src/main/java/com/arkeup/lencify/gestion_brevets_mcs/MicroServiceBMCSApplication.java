package com.arkeup.lencify.gestion_brevets_mcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

import com.arkeup.lencify.gestion_brevets_mcs.commun.ssl.SSLUtilities;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@PropertySource("classpath:param_fonctionnel.properties")
public class MicroServiceBMCSApplication implements ApplicationRunner {

    public static void main(String[] args) {

        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();
        SpringApplication.run(MicroServiceBMCSApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
