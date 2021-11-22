package com.sid.mcsbank;

import com.sid.mcsbank.web.AccountRestJaxRSAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig extends ResourceConfig {

    /*
        Deploy jersey servlet
     */
    public MyConfig() {
        register(AccountRestJaxRSAPI.class);
    }
}
