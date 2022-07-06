package com.employee.management.mapper;

import com.employee.management.domain.Department;
import com.employee.management.domain.Employee;
import com.employee.management.model.EmployeePatchDTO;
import com.employee.management.repo.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeePatchDTOMapper {

    private final DepartmentRepository departmentRepository;

    public Employee mapToEntity(EmployeePatchDTO employeePatchDTO, Employee employee)
    {
        employee.setId(employeePatchDTO.getId());
        employee.setRole(employeePatchDTO.getRole());
        return employee;
    }
}
