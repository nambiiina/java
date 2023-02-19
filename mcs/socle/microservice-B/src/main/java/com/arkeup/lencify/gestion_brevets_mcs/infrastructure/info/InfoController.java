package com.arkeup.lencify.gestion_brevets_mcs.infrastructure.info;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.lencify.gestion_brevets_mcs.donnee.dto.data.InfoDTO;
import com.arkeup.lencify.gestion_brevets_mcs.service.businessdelegate.MicroserviceAMCS;

@RestController
@RequestMapping(value = "/api/b/")
@Api(value = "MicroserviceB")
public class InfoController {

    @Autowired
    private MicroserviceAMCS microserviceAMCS;

    @ApiOperation(value = "Get info nom", notes = "This web service is used to get info nom.")
    @GetMapping("/get/info")
    public InfoDTO getInfoNom(
            @ApiParam(required = true, name = "nom") @RequestParam("infoId") Long infoId
    ) {
    	InfoDTO infoDTO = new InfoDTO();
    	
    	if(infoId == 1) {
    		infoDTO.setLabel("Label 1 from service B");
    	} else {
    		infoDTO.setLabel("Autre label que 1 from service B");
		}
    	
        return infoDTO;
    }
    
    @ApiOperation(value = "Get info from microservice A", notes = "This web service is used to get info from microservice A.")
    @GetMapping("/get/name")
    public InfoDTO getInfoFromServiceA(
    		@ApiParam(name = "userName") @RequestParam(name="userName") String userName
    		) {
    	
    	return microserviceAMCS.getInfoFromServiceA(userName);
    }

}
