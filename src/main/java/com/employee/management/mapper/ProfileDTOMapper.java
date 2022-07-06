package com.employee.management.mapper;

import com.employee.management.domain.Address;
import com.employee.management.domain.Employee;
import com.employee.management.domain.SkillSet;
import com.employee.management.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfileDTOMapper {

    private final AddressDTOMapper addressDTOMapper;
    private final SkillSetDTOMapper skillSetDTOMapper;

    public ProfileDTO mapToProfile(final Employee employee, final ProfileDTO profileDTO) {
        profileDTO.setId(employee.getId());
        profileDTO.setFirstName(employee.getFirstName());
        profileDTO.setLastName(employee.getLastName());
        profileDTO.setRole(employee.getRole());
        profileDTO.setEmail(employee.getEmployeeDetails() == null ? null : employee.getEmployeeDetails().getEmail());
        profileDTO.setDepartmentName(employee.getDepartment().getDepartmentName());
        profileDTO.setAddress(employee.getEmployeeAddress() == null
                ? null : employee.getEmployeeAddress() .stream().map(address ->
                addressDTOMapper.mapToDTO(address ,new AddressDTO())).collect(Collectors.toSet()));
        profileDTO.setSkillName(employee.getEmployeeSkillset() == null ? null : employee.getEmployeeSkillset().stream()
                .map(SkillSet::getSkillName).collect(Collectors.toSet()));
        return profileDTO;
    }
}
