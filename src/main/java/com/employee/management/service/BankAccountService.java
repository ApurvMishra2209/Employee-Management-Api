package com.employee.management.service;

import com.employee.management.model.BankAccountDTO;
import com.employee.management.model.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface BankAccountService {

    PaginatedResponse<?> findAll(final Pageable pageable);

    Long create( final BankAccountDTO bankAccountDTO);

    void update(final Long id, final BankAccountDTO bankAccountDTO);

    void delete(final Long id);

    BankAccountDTO get(final Long id);
}
