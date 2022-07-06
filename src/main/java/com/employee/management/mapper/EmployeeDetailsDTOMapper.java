package com.employee.management.mapper;

import com.employee.management.domain.Employee;
import com.employee.management.domain.EmployeeDetails;
import com.employee.management.model.EmployeeDetailsDTO;
import com.employee.management.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class EmployeeDetailsDTOMapper {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsDTO mapToDTO(final EmployeeDetails employeeDetails,
                                       final EmployeeDetailsDTO employeeDetailsDTO) {
        employeeDetailsDTO.setId(employeeDetails.getId());
        employeeDetailsDTO.setDegree(employeeDetails.getDegree());
        employeeDetailsDTO.setEducation(employeeDetails.getEducation());
        employeeDetailsDTO.setEmail(employeeDetails.getEmail());
        employeeDetailsDTO.setPancard(employeeDetails.getPancard());
        employeeDetailsDTO.setAadharCard(employeeDetailsDTO.getAadharCard());
        employeeDetailsDTO.setWorkingPhoneNo(employeeDetails.getWorkingPhoneNo());
        employeeDetailsDTO.setEmergencyPhoneNo(employeeDetails.getEmergencyPhoneNo());
        employeeDetailsDTO.setJoiningDate(employeeDetails.getJoiningDate());
        employeeDetailsDTO.setEmployee(employeeDetails.getEmployee() == null
                ? null : employeeDetails.getEmployee().getId());
        return employeeDetailsDTO;

    }
 public  EmployeeDetails mapToEntity(final EmployeeDetailsDTO employeeDetailsDTO, final EmployeeDetails employeeDetails){
        employeeDetails.setId(employeeDetailsDTO.getId());
        employeeDetails.setDegree(employeeDetailsDTO.getDegree());
        employeeDetails.setEmail(employeeDetailsDTO.getEmail());
        employeeDetails.setEducation(employeeDetailsDTO.getEducation());
        employeeDetails.setPancard(employeeDetailsDTO.getPancard());
        employeeDetails.setWorkingPhoneNo(employeeDetailsDTO.getWorkingPhoneNo());
        employeeDetails.setEmergencyPhoneNo(employeeDetailsDTO.getEmergencyPhoneNo());
        employeeDetails.setJoiningDate(employeeDetailsDTO.getJoiningDate());
        employeeDetails.setAadharCard(employeeDetailsDTO.getAadharCard());
        if(employeeDetailsDTO.getEmployee() != null
             && (employeeDetails.getEmployee() == null ||
             !employeeDetails.getEmployee().getId().equals(employeeDetailsDTO.getEmployee())))
        {
            final Employee employee = employeeRepository.findById(employeeDetailsDTO.getEmployee())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found"));
            employeeDetails.setEmployee(employee);
        }
        return employeeDetails;
 }
}

