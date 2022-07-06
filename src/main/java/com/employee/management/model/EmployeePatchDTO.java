package com.employee.management.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePatchDTO {

    private  Long id;

    private String employeeName;

    private Role role;
}
