package hu.cubix.hr.bognarlili;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubix.hr.bognarlili.config.EmployeeConfigurationProperties;
import hu.cubix.hr.bognarlili.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{
	
	@Autowired
	SalaryService salaryService;
	
	@Autowired                                   
	EmployeeConfigurationProperties config;

	public HrApplication(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	    for (Double limit : config.getSalary().getSmart().getLimits().keySet()) {
	        System.out.println("Limit: " + limit);
	    }
		
		Employee e1 = new Employee(1L, "Kiss Anna", "dev", 100_000, LocalDateTime.now().minusYears(11));
	    Employee e2 = new Employee(2L, "Kiss Anna","dev", 100_000, LocalDateTime.now().minusYears(6));
	    Employee e3 = new Employee(3L, "Kiss Anna", "dev", 100_000, LocalDateTime.now().minusYears(3));
	    Employee e4 = new Employee(4L, "Kiss Anna", "dev", 100_000, LocalDateTime.now().minusYears(1));

	    System.out.println("e1: " + salaryService.setNewSalary(e1));
	    System.out.println("e2: " + salaryService.setNewSalary(e2));
	    System.out.println("e3: " + salaryService.setNewSalary(e3));
	    System.out.println("e4: " + salaryService.setNewSalary(e4));
		
	}
	


}
