package tk.microservices.proxyInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tk.microservices.bean.CurrencyConverterBean;

@FeignClient(name = "currency-exchange-service",url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConverterBean retriveExchangeValue(@PathVariable String from, @PathVariable String to);
}
