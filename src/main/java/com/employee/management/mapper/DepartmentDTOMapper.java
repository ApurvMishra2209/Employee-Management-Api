package com.employee.management.mapper;

import com.employee.management.domain.*;
import com.employee.management.model.DepartmentDTO;
import com.employee.management.repo.DepartmentRepository;
import com.employee.management.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentDTOMapper {

    private final DepartmentRepository departmentRepository;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeRepository employeeRepository;

    public DepartmentDTO mapToDTO(final Department department, final DepartmentDTO departmentDTO)
    {
        departmentDTO.setId(department.getId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        departmentDTO.setManager(department.getManager());
        departmentDTO.setEmployeeIds(department.getEmployee() == null
        ? null : department.getEmployee().stream().map(Employee::getId).collect(Collectors.toList()));
        return departmentDTO;
    }

    public Department mapToEntity(final DepartmentDTO departmentDTO,final Department department) {

        department.setId(departmentDTO.getId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setManager(departmentDTO.getManager()  == null ? null : departmentDTO.getManager());
        if(departmentDTO.getEmployeeIds() != null)
        {
            final List<Employee> employeeset=employeeRepository.findAllById(departmentDTO.getEmployeeIds());
            if(employeeset.size() != departmentDTO.getEmployeeIds().size())
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of employee not found");
            }
            department.setEmployee(new HashSet<>(employeeset));
        }

        return department;
    }
}
