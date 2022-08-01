package tk.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.microservices.entity.ExchangeValue;

public interface ExchangeServiceRepo extends JpaRepository<ExchangeValue, Long>{
	
	ExchangeValue findByFromAndTo(String From,String To);

}
