package com.workmotion.projects.hrplatform.controller;

import com.workmotion.projects.hrplatform.exception.EmployeeNotFoundException;
import com.workmotion.projects.hrplatform.model.Employee;
import com.workmotion.projects.hrplatform.model.EmployeeState;
import com.workmotion.projects.hrplatform.model.EmployeeStateChangeDto;
import com.workmotion.projects.hrplatform.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee newEmployee) {
        newEmployee.setEmployeeState(EmployeeState.ADDED);
        return new ResponseEntity<>(employeeService.save(newEmployee), HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Long id) {

        return employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
    }

    @PutMapping("/employee/state-change")
    public ResponseEntity<String> updateEmployeeState(@RequestBody EmployeeStateChangeDto stateChangeDto){
        Employee employee = employeeService.findById(stateChangeDto.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        EmployeeState currentState = employee.getEmployeeState();

        if(!currentState.nextState().contains(stateChangeDto.getTargetState())){
            return new ResponseEntity<>("Requested State transition is not supported. The allowed state transitions are:" + "\n" +
                    "ADDED -> IN-CHECK <-> APPROVED -> ACTIVE" , HttpStatus.BAD_REQUEST);
        }
        employee.setEmployeeState(stateChangeDto.getTargetState());
        return new ResponseEntity<>("State Updated Successfully",HttpStatus.OK);

    }

    @GetMapping("/employees")
    public List<Employee> getAllUsers() {
        return employeeService.findAll();
    }

}
