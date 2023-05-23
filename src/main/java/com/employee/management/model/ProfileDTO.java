package com.employee.management.model;

import com.employee.management.domain.SkillSet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class ProfileDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Role role;

    private String email;

    private  Set<AddressDTO> address;

    private String departmentName;

    private Set<String> skillName;

}


