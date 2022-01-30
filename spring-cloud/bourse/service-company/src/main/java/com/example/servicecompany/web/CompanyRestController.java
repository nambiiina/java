package com.example.servicecompany.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope // refresh params when mcs reload config
@RestController
public class CompanyRestController {

    @Value("${admin}")
    private String admin;

    /*@Value("${spring.cloud.discovery.enabled}")
    private boolean springCloudDiscovery;*/

    @LocalServerPort
    private int port;

    @GetMapping(path = "/myConfig")
    public Map<String, Object> getConfig() {
        Map<String, Object> myConfig = new HashMap<>();
        myConfig.put("amdin", admin);
//        myConfig.put("spring.cloud.discovery.enabled", springCloudDiscovery);
        myConfig.put("threadName", Thread.currentThread().getName());
        myConfig.put("port", port);
        return myConfig;
    }

}
