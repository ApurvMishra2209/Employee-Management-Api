package com.employee.management.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DepartmentDTO {


    private Long id;

    private DepartmentName departmentName;

    private List<Long> employeeIds;

    private UUID manager;


}
