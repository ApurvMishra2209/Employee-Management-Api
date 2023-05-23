package com.employee.management.mapper;

import com.employee.management.domain.Department;
import com.employee.management.domain.Employee;
import com.employee.management.exception.NotFoundException;
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

    public Employee mapToEntity(EmployeePatchDTO employeePatchDTO, Employee employee) {
        // Update the employee's role if provided in the request
        if (employeePatchDTO.getRole() != null) {
            employee.setRole(employeePatchDTO.getRole());
        }

        // Update the employee's department if provided in the request
        if (employeePatchDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeePatchDTO.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException("Department not found with ID: " + employeePatchDTO.getDepartmentId()));
            employee.setDepartment(department);
        }

        return employee;
    }
}

