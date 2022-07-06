package com.employee.management.service;

import com.employee.management.model.DepartmentDTO;
import com.employee.management.model.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    Long create( final DepartmentDTO departmentDTO);

    void update(final Long id, final DepartmentDTO departmentDTO);

    void delete(final Long id);

    DepartmentDTO get(final Long id);

    PaginatedResponse<?> findAll(Pageable pageable);
}
