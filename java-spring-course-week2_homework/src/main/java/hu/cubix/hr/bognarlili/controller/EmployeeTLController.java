package hu.cubix.hr.bognarlili.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.cubix.hr.bognarlili.dto.EmployeeDto;

@Controller
public class EmployeeTLController {
	
	private List<EmployeeDto> employees = new ArrayList<>();
	
	{
		employees.add(new EmployeeDto(1L, "asd", 123, LocalDateTime.of(2002, 10, 18, 0, 0)));
	}
	
	@GetMapping("/")
	public String home(Map<String, Object> model) {
		model.put("employees", employees);
		model.put("newEmployee", new EmployeeDto());
		return "index";
	}
	
	@PostMapping("/employee")
	public String createEmployee(EmployeeDto employee) {
		employees.add(employee);
		return "redirect:/";
		
	}
	
	

}
