package hu.cubix.hr.bognarlili.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.cubix.hr.bognarlili.dto.EmployeeDto;

@Controller
public class EmployeeTLController {
	
	private List<EmployeeDto> employees = new ArrayList<>();
	
	{
		employees.add(new EmployeeDto(1L, "Kiss Adél", "asd", 123, LocalDateTime.of(2002, 10, 18, 0, 0)));
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", employees);
		model.put("newEmployee", new EmployeeDto());
		return "employees";
	}
	
	@PostMapping("/employees")
	public String createEmployee(EmployeeDto employee) {
		employees.add(employee);
		return "redirect:employees";
		
	}
	
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable long id) {
		employees.removeIf(e -> e.getId().equals(id));
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/edit/{id}")
	public String showEditorForm(@PathVariable Long id, Model model) {
		EmployeeDto employee = employees.stream()
				.filter(e -> e.getId().equals(id))
				.findFirst().get();
		
        model.addAttribute("employee", employee);
        return "employee-edit";
	}

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @ModelAttribute("employee") EmployeeDto updated) {
        employees.removeIf(e -> e.getId().equals(id));
        updated.setId(id);    
        employees.add(updated);
        return "redirect:/employees";
    }
	
	

}
