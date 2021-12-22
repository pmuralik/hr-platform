package com.workmotion.projects.hrplatform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.projects.hrplatform.controller.EmployeeController;
import com.workmotion.projects.hrplatform.model.Employee;
import com.workmotion.projects.hrplatform.model.EmployeeState;
import com.workmotion.projects.hrplatform.model.EmployeeStateChangeDto;
import com.workmotion.projects.hrplatform.repository.EmployeeRepository;
import com.workmotion.projects.hrplatform.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    Employee EMPLOYEE_4 = new Employee(4l, EmployeeState.ACTIVE,"Ajit Pandey", "50", 11);

    @Test
    public void getEmployee_success() throws Exception {
        Employee employee = new Employee(1l, EmployeeState.ADDED,"Rayven Yor", "23", 10);

        Mockito.when(employeeService.findById(1l)).thenReturn(Optional.ofNullable(employee));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName", is("Rayven Yor")));
    }

    @Test
    public void createEmploee_success() throws Exception {
        Employee employee = new Employee(2l, EmployeeState.IN_CHECK,"Jane Doe", "31", 12);
        Mockito.when(employeeService.save(Mockito.any(Employee.class))).thenReturn(employee);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(employee));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeName", is("Jane Doe")));
    }

    @Test
    public void updatePatientRecord_success() throws Exception {
        Employee employee = new Employee(3l, EmployeeState.ADDED,"David Landup", "27", 11);
        EmployeeStateChangeDto stateChangeDto = new EmployeeStateChangeDto(3l,EmployeeState.IN_CHECK);

        Mockito.when(employeeService.findById(3l)).thenReturn(Optional.ofNullable(employee));
        Mockito.when(employeeService.save(Mockito.any(Employee.class))).thenReturn(employee);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/employee/state-change")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(stateChangeDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}