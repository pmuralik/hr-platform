package com.workmotion.projects.hrplatform;

import com.workmotion.projects.hrplatform.model.Employee;
import com.workmotion.projects.hrplatform.model.EmployeeState;
import com.workmotion.projects.hrplatform.repository.EmployeeRepository;
import com.workmotion.projects.hrplatform.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmployeeRepositoryTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Create User Test ")
    void createUserRepositoryTest() {
        Employee created = employeeRepository.save(getUser());
        assertTrue(created != null);
    }

    private Employee getUser() {
        Employee employee = new Employee();
        employee.setEmployeeName("MURALI");
        employee.setContactNumber("897776671");employee.setAge(22);
        return employee;
    }
}


