package es.netmind.banana_invoices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import es.netmind.banana_invoices.persistence.IPropietarioRepo;
import es.netmind.banana_invoices.persistence.IReciboRepo;
import es.netmind.banana_invoices.persistence.JPAIReciboRepo;
import es.netmind.banana_invoices.persistence.JPAPropietarioRepo;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class ReposConfig {
	
	@Bean
	public IReciboRepo reciboRepo() {
		return new JPAIReciboRepo();
	}
	
	@Bean
	public IPropietarioRepo propietarioRepo() {
		return new JPAPropietarioRepo();
	}
	
}
