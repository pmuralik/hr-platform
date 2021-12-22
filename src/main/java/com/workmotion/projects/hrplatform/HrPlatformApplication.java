package com.workmotion.projects.hrplatform;

import com.workmotion.projects.hrplatform.model.Employee;
import com.workmotion.projects.hrplatform.model.EmployeeState;
import com.workmotion.projects.hrplatform.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HrPlatformApplication {

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		System.out.println("My Appplication started");
		SpringApplication.run(HrPlatformApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
		Employee employee = new Employee();
		employee.setEmployeeName("MURALI");
		employee.setContactNumber("897776671");
		employee.setAge(22);
		employee.setEmployeeState(EmployeeState.ADDED);

		employeeRepository.save(employee);
	}

}
