package com.etech.microservice.micro_a.infrastructure.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.etech.microservice.micro_a.donnee.dto.data.InfoDTO;
import com.etech.microservice.micro_a.service.businessdelegate.MicroserviceBMCS;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/a/")
@Api("MicroserviceA")
public class InfoController {
	
	@Autowired
	private MicroserviceBMCS microserviceBMCS;

    @ApiOperation(value = "Get info nom", notes = "This web service is used to get info nom.")
    @GetMapping(path = "/get/name/{userName}")
    @ResponseBody
    public InfoDTO getInformation(
    		@ApiParam(name="userName") @PathVariable(name="userName") String userName
    		) {
    	InfoDTO infoDTO = new InfoDTO();
    	infoDTO.setLabel(userName + " from service A");
    	
        return infoDTO;
    }
    
    @ApiOperation(value = "Get info from microservice B", notes = "This web service is used to get info from microservice B.")
    @GetMapping(path = "/getInformation")
    @ResponseBody
    public InfoDTO getInfoFromServiceB(
    		@ApiParam(name="id", required=true) @RequestParam(name="id", required=true) Long id
    		) {
    	return microserviceBMCS.getInfoFromServiceB(id);
    }

}
