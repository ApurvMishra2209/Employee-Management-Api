package com.employee.management.repo;

import com.employee.management.domain.Department;
import com.employee.management.model.DepartmentName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Page<Department> findAllByOrderByDateCreatedDesc(Pageable pageable);

    Department findByDepartmentName(DepartmentName department);
}
