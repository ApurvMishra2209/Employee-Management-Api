package com.employee.management.mapper;



import com.employee.management.domain.BankAccount;
import com.employee.management.domain.Employee;
import com.employee.management.model.BankAccountDTO;
import com.employee.management.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class BankAccountDTOMapper {

    private final EmployeeRepository employeeRepository;

    public BankAccountDTO mapToDTO(final BankAccount bankAccount,final BankAccountDTO bankAccountDTO)
    {
        bankAccountDTO.setId(bankAccount.getId());
        bankAccountDTO.setAccountNumber(bankAccount.getAccountNumber());
        bankAccountDTO.setBankName(bankAccount.getBankName());
        bankAccountDTO.setAccountHolderName(bankAccount.getAccountHolderName());
        bankAccountDTO.setBranchName(bankAccount.getBranchName());
        bankAccountDTO.setIfscCode(bankAccount.getIfscCode());
        bankAccountDTO.setAccountType(bankAccount.getAccountType());
        bankAccountDTO.setEmployee(bankAccount.getEmployee() == null ? null : bankAccount.getEmployee().getId());
        return bankAccountDTO;
    }

    public BankAccount mapToEntity(final BankAccountDTO bankAccountDTO,final BankAccount bankAccount)
    {
        bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());
        bankAccount.setAccountType(bankAccountDTO.getAccountType());
        bankAccount.setBankName(bankAccountDTO.getBankName());
        bankAccount.setIfscCode(bankAccountDTO.getIfscCode());
        bankAccount.setId(bankAccountDTO.getId());
        bankAccount.setAccountHolderName(bankAccountDTO.getAccountHolderName());
        bankAccount.setBranchName(bankAccountDTO.getBranchName());
        if(bankAccountDTO.getEmployee() != null
        && (bankAccount.getEmployee() == null || !bankAccount.getEmployee().getId().equals(bankAccountDTO.getEmployee())))
        {

            final Employee employee=employeeRepository.findById(bankAccountDTO.getEmployee())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found"));
            bankAccount.setEmployee(employee);
        }

        return bankAccount;
    }
}

