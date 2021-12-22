package com.workmotion.projects.hrplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeStateChangeDto {
    private Long employeeId;
    private EmployeeState targetState;
}
