package tk.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tk.microservices.dao.ExchangeServiceRepo;
import tk.microservices.entity.ExchangeValue;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeServiceRepo exchangeServieceRepo;

//	@GetMapping("currency-exchange/from/{from}/to/{to}")
//	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
//		// HardCode ExchangeValue exchangeValue = new
//		ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
//		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
//		return exchangeValue;
//
//	}
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue =exchangeServieceRepo.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;

	}

}
