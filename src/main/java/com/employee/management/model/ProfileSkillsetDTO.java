package com.employee.management.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileSkillsetDTO {

    private Long id;

    private String skillName;

    private List<Long> employeeIds;
}
