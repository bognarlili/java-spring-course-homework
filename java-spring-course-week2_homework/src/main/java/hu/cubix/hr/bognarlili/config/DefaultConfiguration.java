package hu.cubix.hr.bognarlili.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.cubix.hr.bognarlili.service.DefaultEmployeeService;
import hu.cubix.hr.bognarlili.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
		
	}
}
