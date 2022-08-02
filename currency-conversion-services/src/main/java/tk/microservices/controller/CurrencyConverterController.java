package tk.microservices.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConverterController {
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
		return new CurrencyConverterBean(1L, from, to, BigDecimal.ONE, quantity, quantity, 0);
	}
}
