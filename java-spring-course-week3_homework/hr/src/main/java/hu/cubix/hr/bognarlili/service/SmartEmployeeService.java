package hu.cubix.hr.bognarlili.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import hu.cubix.hr.bognarlili.Employee;
import hu.cubix.hr.bognarlili.config.EmployeeConfigurationProperties;
import hu.cubix.hr.bognarlili.config.EmployeeConfigurationProperties.Smart;

public class SmartEmployeeService implements EmployeeService{
	
	@Autowired
	private EmployeeConfigurationProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
        
		LocalDate startDate = employee.getStartedWorking().toLocalDate();
        LocalDate today = LocalDate.now();
        Period period = Period.between(startDate, today);

        double workedYears = period.getYears() + period.getMonths() / 12.0;
        
        Smart smart = config.getSalary().getSmart();
        
//        if (years > smart.getLimit3()) {
//            return smart.getPercent3();
//            
//        } else if (years >= smart.getLimit2()) {
//            return smart.getPercent2();
//            
//        } else if (years >= smart.getLimit1()) {
//            return smart.getPercent1();
//            
//        } else {
//            return 0;
//        }
        
        TreeMap<Double, Integer> limitsMap = smart.getLimits();
        
        Integer maxLimit =null;
        for(var entry: limitsMap.entrySet()) {
        	if(workedYears >= entry.getKey()) {
        		maxLimit = entry.getValue();
        	} else {
        		break;
        	}
        }

        return maxLimit == null ? 0 : maxLimit;
 
	}
	
	
}
