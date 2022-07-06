package com.employee.management.service;

import com.employee.management.model.AddressDTO;
import com.employee.management.model.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    PaginatedResponse<?> findAll(final Pageable pageable);

    Long create( final AddressDTO addressDTO);

    void update(final Long id, final AddressDTO addressDTO);

    void delete(final Long id);

    AddressDTO get(final Long id);

}
