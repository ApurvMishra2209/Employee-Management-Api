package com.employee.management.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RequestDepartmentDTO {

    private String departmentName;

    private List<Long> employeeIds;

    private String managerUuid;




}
