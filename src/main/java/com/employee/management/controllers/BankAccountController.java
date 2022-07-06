package com.employee.management.controllers;

import com.employee.management.model.BankAccountDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.service.BankAccountService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/bankaccount", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<PaginatedResponse<?>> getAllBankAccounts(final Pageable pageable)
    {
        return ResponseEntity.ok(bankAccountService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDTO> getBankAccount(@PathVariable final Long id) {
        return ResponseEntity.ok(bankAccountService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createBankAccount(
            @RequestBody @Valid final BankAccountDTO bankAccountDTO) {
        return new ResponseEntity<>(bankAccountService.create(bankAccountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBankAccount(@PathVariable final Long id,
                                               @RequestBody @Valid final BankAccountDTO bankAccountDTO) {
        bankAccountService.update(id, bankAccountDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable final Long id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
