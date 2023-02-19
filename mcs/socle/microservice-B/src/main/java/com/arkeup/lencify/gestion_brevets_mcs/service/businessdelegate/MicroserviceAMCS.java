package com.arkeup.lencify.gestion_brevets_mcs.service.businessdelegate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arkeup.lencify.gestion_brevets_mcs.donnee.dto.data.InfoDTO;

@FeignClient(name = "MicroserviceAMCS", configuration = GlobalClientConfiguration.class, url = "#{'${microa.mcs.url}'}")
public interface MicroserviceAMCS {

    @GetMapping(path = "/api/a/get/name/{userName}")
    InfoDTO getInfoFromServiceA(@PathVariable(name = "userName") String userName);

}
