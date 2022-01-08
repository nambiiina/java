package com.sid.mcsbank.web;

import com.sid.mcsbank.entities.Log;
import com.sid.mcsbank.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/*
    1- create jax ws based in soap
    2- deploy jax ws using spring
    3- url : http://localhost:8888/bankSoapWS?wsdl
 */

@Component
@WebService(serviceName = "bankLogSoapWS")
public class LogSoapJaxWSAPI {

    @Autowired
    private LogRepository logRepository;

    @WebMethod
    public List<Log> logs() {
        return logRepository.findAll();
    }
}
