package com.employee.management.mapper;

import com.employee.management.domain.Employee;
import com.employee.management.domain.SkillSet;
import com.employee.management.model.ProfileSkillsetDTO;
import com.employee.management.model.SkillSetDTO;
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
public class SkillSetDTOMapper {

    private final EmployeeRepository employeeRepository;

    public SkillSetDTO mapToDTO(final SkillSet skillSet, final SkillSetDTO skillSetDTO)
    {
        skillSetDTO.setSkillName(skillSet.getSkillName());
        return skillSetDTO;
    }

    public SkillSet mapToEntity(final SkillSetDTO skillSetDTO, final SkillSet skillSet)
    {
        skillSet.setSkillName(skillSetDTO.getSkillName());
        return skillSet;
    }

    public ProfileSkillsetDTO mapToProfile(final SkillSet skillSet, final ProfileSkillsetDTO profileSkillsetDTO)
    {
        profileSkillsetDTO.setId(skillSet.getId());
        profileSkillsetDTO.setSkillName(skillSet.getSkillName());
        profileSkillsetDTO.setEmployeeIds(skillSet.getSkillsetOfemployee() == null ? null :
                skillSet.getSkillsetOfemployee().stream().map(Employee::getId).collect(Collectors.toList()));
        return profileSkillsetDTO;
    }
}
