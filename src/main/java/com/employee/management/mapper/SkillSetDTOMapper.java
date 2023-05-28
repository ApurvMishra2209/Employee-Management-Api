package com.employee.management.mapper;

import com.employee.management.domain.Employee;
import com.employee.management.domain.SkillSet;
import com.employee.management.exception.AlreadyExistsException;
import com.employee.management.model.ProfileSkillsetDTO;
import com.employee.management.model.SkillSetDTO;
import com.employee.management.repo.EmployeeRepository;
import com.employee.management.repo.SkillSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SkillSetDTOMapper {

    private final EmployeeRepository employeeRepository;
    private final SkillSetRepository skillSetRepository;


    public SkillSetDTO mapToDTO(final SkillSet skillSet, final SkillSetDTO skillSetDTO){
        skillSetDTO.setSkillName(skillSet.getSkillName());
        return skillSetDTO;
    }

    public SkillSet mapToEntity(final SkillSetDTO skillSetDTO, final SkillSet skillSet)
    {
        if (skillSetRepository.existsBySkillNameIgnoreCase(skillSetDTO.getSkillName())) {
            // Skill already exists, log an error or throw an exception
            // You can log an error message or throw a custom exception here
            // For example, logging an error message:
            System.err.println("Skill already exists: " + skillSetDTO.getSkillName());
            // or throwing a custom exception:
//            throw new Exception("Skill already exists: " + skillSetDTO.getSkillName());
            throw new AlreadyExistsException("Skill already exists: " + skillSetDTO.getSkillName());
        }

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
