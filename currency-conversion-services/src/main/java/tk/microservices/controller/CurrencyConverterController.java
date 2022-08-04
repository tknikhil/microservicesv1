package tk.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tk.microservices.bean.CurrencyConverterBean;
import tk.microservices.proxyInterface.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConverterController {
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		//Problem :- Line of code
		Map<String, String> uriVariable = new HashMap();
		uriVariable.put("from", from);
		uriVariable.put("to", to);

		ResponseEntity<CurrencyConverterBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConverterBean.class, uriVariable);//calling currency-exchange service
		CurrencyConverterBean responsebody = responseEntity.getBody();

		return new CurrencyConverterBean(responsebody.getId(), from, to, responsebody.getConversionMultiple(), quantity,
				quantity.multiply(responsebody.getConversionMultiple()), responsebody.getPort());
		//Problem end
	}
	
	//Solution :- Feign Line of code
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConverterBean responsebody = proxy.retriveExchangeValue(from, to);

		return new CurrencyConverterBean(responsebody.getId(), from, to, responsebody.getConversionMultiple(), quantity,
				quantity.multiply(responsebody.getConversionMultiple()), responsebody.getPort());
		//Solution end
	}
}
