package hu.cubix.hr.bognarlili.service;

import org.springframework.beans.factory.annotation.Autowired;

import hu.cubix.hr.bognarlili.Employee;
import hu.cubix.hr.bognarlili.config.EmployeeConfigurationProperties;
import hu.cubix.hr.bognarlili.config.EmployeeConfigurationProperties.Smart;

public class DefaultEmployeeService implements EmployeeService {

	@Autowired
	EmployeeConfigurationProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getSalary().getDef().getPercent();
	}

}
