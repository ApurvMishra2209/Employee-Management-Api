package com.employee.management.service;

import com.employee.management.model.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmployeeService {

    ProfileDTO getProfile(final UUID id);

    PaginatedResponse<?> findAll(final Pageable pageable);

    Long createUser(final AuthenticationRequestDTO authenticationRequestDTO);

    void update(final Long id, final EmployeeDTO employeeDTO);

    void delete(final Long id);

    ProfileDTO get(final Long id, final String token);

    void patchUpdateEmployee(final Long id,final EmployeePatchDTO employeePatchDTO);
}
