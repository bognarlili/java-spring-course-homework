package hu.cubix.hr.bognarlili.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.cubix.hr.bognarlili.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private Map<Long, EmployeeDto> employees = new HashMap<>();
	
	{
		employees.put(1L, new EmployeeDto(1L, "asd", 123, LocalDateTime.of(2002, 10, 18, 0, 0)));
	}
	
	@GetMapping
	public List<EmployeeDto> findAll(){
		return new ArrayList<>(employees.values());
	}
	
	@GetMapping("/{id}")
	public EmployeeDto findById(@PathVariable long id){
		return employees.get(id);
	}
	
	@GetMapping(params = "minSalary")
	public List<EmployeeDto> findByHigherSalary(@RequestParam int minSalary) {
	    List<EmployeeDto> higherSalaryEmployees = new ArrayList<>();

	    for (EmployeeDto employee : employees.values()) {
	        if (employee.getSalary() > minSalary) {
	            higherSalaryEmployees.add(employee);
	        }
	    }

	    return higherSalaryEmployees;
	}
	
	
	@PostMapping
	public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employee) {
		if(employees.containsKey(employee.getId()))
		{
			return ResponseEntity.badRequest().build();
		}
		employees.put(employee.getId(), employee);
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> update(@PathVariable long id, @RequestBody EmployeeDto airport) {

		airport.setId(id);
		if(!employees.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		employees.put(id, airport);
		return ResponseEntity.ok(airport);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		employees.remove(id);
	}

	
}
