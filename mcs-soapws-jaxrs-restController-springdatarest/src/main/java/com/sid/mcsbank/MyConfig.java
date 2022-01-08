package com.sid.mcsbank;

import com.sid.mcsbank.web.AccountRestJaxRSAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@Configuration
public class MyConfig extends ResourceConfig {

    /*
        Deploy jersey servlet
     */
    public MyConfig() {
        register(AccountRestJaxRSAPI.class);
    }

    /*
        Deploy server jax ws (soap)
     */
    @Bean
    SimpleJaxWsServiceExporter serviceExporter(){
        SimpleJaxWsServiceExporter serviceExporter = new SimpleJaxWsServiceExporter();
        serviceExporter.setBaseAddress("http://0.0.0.0:8989/");
        return serviceExporter;
    }
}
