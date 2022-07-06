package com.employee.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDetailsDTO {

    private Long id;


    private String email;

    private String pancard;

    private Long aadharCard;

    private Long workingPhoneNo;

    private Long emergencyPhoneNo;

    private LocalDate joiningDate;

    private String education;

    private String degree;

    private Long employee;


}
