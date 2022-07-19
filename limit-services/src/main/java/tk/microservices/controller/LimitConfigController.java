package tk.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.microservices.bean.LimitsConfiguration;
import tk.microservices.bean.ReadPropertyConfiguration;

@RestController
public class LimitConfigController {
    @Autowired
    private ReadPropertyConfiguration readPropertyConfiguration;
//Comming from LimitConfiguration bean
//	@GetMapping("/limits")
//	public LimitsConfiguration retriveLimitsFromConfiguration() {
//		return new LimitsConfiguration(1000, 1);
//	}
    
    @GetMapping("/limits")
	public LimitsConfiguration retriveLimitsFromConfiguration() {
		return new LimitsConfiguration(readPropertyConfiguration.getMaximum(), readPropertyConfiguration.getMinimum());
	}
    
}
