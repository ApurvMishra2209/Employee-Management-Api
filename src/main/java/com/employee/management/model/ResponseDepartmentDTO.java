package com.employee.management.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ResponseDepartmentDTO {

    private Long id;

    private String departmentName;

    private List<Long> employeeIds;

    private UUID managerUUID;

    private String managerFirstName;

    private String managerLastName;
}
