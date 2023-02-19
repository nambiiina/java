package com.etech.microservice.micro_a.service.businessdelegate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etech.microservice.micro_a.donnee.dto.data.InfoDTO;

@FeignClient(name = "MicroserviceBMCS", url = "#{'${microb.mcs.url}'}")
public interface MicroserviceBMCS {

    @GetMapping(path = "/api/b/get/info")
    InfoDTO getInfoFromServiceB(@RequestParam(name = "infoId") Long infoId);

}
