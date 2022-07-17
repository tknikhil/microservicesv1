package tk.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.microservices.bean.LimitsConfiguration;

@RestController
public class LimitConfigController {

	@GetMapping("/limits")
	public LimitsConfiguration retriveLimitsFromConfiguration() {
		return new LimitsConfiguration(1000, 1);
	}
}
