package com.employee.management.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountDTO {

    private Long id;

    private String accountHolderName;

    private Long accountNumber;

    private String accountType;

    private String bankName;

    private String branchName;

    private String ifscCode;

    private Long employee;
}
