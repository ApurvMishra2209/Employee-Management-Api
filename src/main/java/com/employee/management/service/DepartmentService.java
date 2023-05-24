package com.employee.management.service;

import com.employee.management.model.RequestDepartmentDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.model.ResponseDepartmentDTO;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    Long create( final RequestDepartmentDTO requestDepartmentDTO);

    void update(final Long id, final RequestDepartmentDTO requestDepartmentDTO);

    void delete(final Long id);

    ResponseDepartmentDTO get(final Long id);

    PaginatedResponse<?> findAll(Pageable pageable);
}
