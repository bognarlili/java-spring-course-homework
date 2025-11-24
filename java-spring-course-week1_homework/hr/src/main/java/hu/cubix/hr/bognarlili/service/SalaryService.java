package hu.cubix.hr.bognarlili.service;

import org.springframework.stereotype.Service;

import hu.cubix.hr.bognarlili.Employee;

@Service
public class SalaryService {
	
	private EmployeeService emloyeeService;

	public SalaryService(EmployeeService emloyeeService) {
		this.emloyeeService = emloyeeService;
	}
	
	public int setNewSalary(Employee employee) {
		return (int) (employee.getSalary() / 100.0 * (100 + emloyeeService.getPayRaisePercent(employee)));
		
	}
	
	

}
