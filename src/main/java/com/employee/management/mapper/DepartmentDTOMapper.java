package com.employee.management.mapper;

import com.employee.management.domain.*;
import com.employee.management.exception.AlreadyExistsException;
//import com.employee.management.model.DepartmentName;
import com.employee.management.model.RequestDepartmentDTO;
import com.employee.management.model.ResponseDepartmentDTO;
import com.employee.management.repo.DepartmentRepository;
import com.employee.management.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentDTOMapper {

    private final DepartmentRepository departmentRepository;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeRepository employeeRepository;

    public ResponseDepartmentDTO mapToDTO(final Department department, final ResponseDepartmentDTO responseDepartmentDTO)
    {
        responseDepartmentDTO.setId(department.getId());
        responseDepartmentDTO.setDepartmentName(department.getDepartmentName());
        responseDepartmentDTO.setManagerUUID(department.getManagerUuid());
        responseDepartmentDTO.setManagerFirstName(department.getManagerFirstName());
        responseDepartmentDTO.setManagerLastName(department.getManagerLastName());
        responseDepartmentDTO.setEmployeeIds(department.getEmployee() == null
                ? null : department.getEmployee().stream().map(Employee::getId).collect(Collectors.toList()));
        return responseDepartmentDTO;
    }

    public Department mapToEntity(final RequestDepartmentDTO requestDepartmentDTO, final Department department) {

        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByUuid(requestDepartmentDTO.getManagerUuid());

//        department.setId(requestDepartmentDTO.getId());
        if (departmentRepository.existsByDepartmentNameIgnoreCase(requestDepartmentDTO.getDepartmentName())) {
            // Skill already exists, log an error or throw an exception
            // You can log an error message or throw a custom exception here
            // For example, logging an error message:
            System.err.println("Department already exists: " + requestDepartmentDTO.getDepartmentName());
            // or throwing a custom exception:
//            throw new Exception("Skill already exists: " + skillSetDTO.getSkillName());
            throw new AlreadyExistsException("Department already exists: " + requestDepartmentDTO.getDepartmentName());
        }
        department.setDepartmentName(requestDepartmentDTO.getDepartmentName());
        if(requestDepartmentDTO.getManagerUuid() != null){
            employeeRepository.findEmployeeByUuid(requestDepartmentDTO.getManagerUuid())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager not found"));
            department.setManagerUuid(requestDepartmentDTO.getManagerUuid());
        }
//        department.setManagerUuid(requestDepartmentDTO.getManagerUuid()  == null ? null : requestDepartmentDTO.getManagerUuid());

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            department.setManagerFirstName(employee.getFirstName());
            department.setManagerLastName(employee.getLastName());
        }

        if(requestDepartmentDTO.getEmployeeIds() != null)
        {
            final List<Employee> employeeset=employeeRepository.findAllById(requestDepartmentDTO.getEmployeeIds());
            if(employeeset.size() != requestDepartmentDTO.getEmployeeIds().size())
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of employee not found");
            }
            department.setEmployee(new HashSet<>(employeeset));
        }

        return department;
    }
}
