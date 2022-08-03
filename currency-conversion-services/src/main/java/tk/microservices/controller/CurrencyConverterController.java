package tk.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConverterController {

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariable = new HashMap();
		uriVariable.put("from", from);
		uriVariable.put("to", to);

		ResponseEntity<CurrencyConverterBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConverterBean.class, uriVariable);//calling currency-exchange service
		CurrencyConverterBean responsebody = responseEntity.getBody();

		return new CurrencyConverterBean(responsebody.getId(), from, to, responsebody.getConversionMultiple(), quantity,
				quantity.multiply(responsebody.getConversionMultiple()), responsebody.getPort());
	}
}
