package com.employee.management.service;

import com.employee.management.model.DepartmentDTO;
import com.employee.management.model.EmployeeDetailsDTO;
import com.employee.management.model.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface EmployeeDetailsService {

    PaginatedResponse<?> findAll(final Pageable pageable);

    Long create( final EmployeeDetailsDTO employeeDetailsDTO);

    void update(final Long id, final EmployeeDetailsDTO employeeDetailsDTO);

    void delete(final Long id);

    EmployeeDetailsDTO get(final Long id);
}
