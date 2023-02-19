/**
 * 
 */
package com.etech.microservice.micro_a.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


@RestController
public class HealthController {

	@ApiOperation(value = "health check")
	@GetMapping("/healthz")
	public Boolean healthz() {
		return true;
	}
}
