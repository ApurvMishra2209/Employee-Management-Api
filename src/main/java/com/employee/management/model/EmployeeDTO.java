package com.employee.management.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDTO {

    private String firstName;

    private String lastName;

    private String department;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    private String gender;

    private Set<String> skillName;








//    private Long userDetails;

//    private Long employeeDetails;
//
//    private List<AddressDTO> addressDTOList;
//
//    private List<BankAccountDTO> bankAccountDTOList;

}
