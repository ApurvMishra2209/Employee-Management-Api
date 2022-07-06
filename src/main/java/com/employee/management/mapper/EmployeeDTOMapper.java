package com.employee.management.mapper;

import com.employee.management.domain.*;
import com.employee.management.model.EmployeeDTO;
import com.employee.management.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeDTOMapper {

    private final AddressDTOMapper addressDTOMapper;
    private final BankAccountDTOMapper bankAccountDTOMapper;
    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;
    private final BankAccountRepository bankAccountRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillSetRepository skillSetRepository;

//    public EmployeeDTO mapToDTO(final Employee employee, final EmployeeDTO employeeDTO) {
//        employeeDTO.setFirstName(employee.getFirstName());
//        employeeDTO.setLastName(employee.getLastName());
//        employeeDTO.setDateOfBirth(employee.getDob() == null ? null : employee.getDob());
//        employeeDTO.setGender(employee.getGender());
//        employeeDTO.setDepartment(employee.getDepartment() == null ? null : employee.getDepartment().getId());
//        return employeeDTO;
//
//    }

    public Employee mapToEntity(final EmployeeDTO employeeDTO, final Employee employee) {
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        employee.setDob(employeeDTO.getDateOfBirth() == null ? null : employeeDTO.getDateOfBirth());
        if (employeeDTO.getSkillName() != null) {
            final List<SkillSet> skillSets = skillSetRepository.findAllBySkillNameIn(employeeDTO.getSkillName());
            if (skillSets.size() != employeeDTO.getSkillName().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of skillset not found");
            }
            employee.setEmployeeSkillset(new HashSet<>(skillSets));
        }
            if (employeeDTO.getDepartment() != null
                    && (employee.getDepartment() == null ||
                    !employee.getDepartment().getId().equals(employeeDTO.getDepartment()))) {
                final Department department = departmentRepository.findById(employeeDTO.getDepartment())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
                employee.setDepartment(department);
            }
            return employee;
        }
    }

