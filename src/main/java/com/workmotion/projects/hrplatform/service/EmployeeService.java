package com.workmotion.projects.hrplatform.service;

import com.workmotion.projects.hrplatform.model.Employee;
import com.workmotion.projects.hrplatform.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee newEmployee) {
       return employeeRepository.save(newEmployee);
    }


    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
