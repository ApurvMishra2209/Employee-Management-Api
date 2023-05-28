package com.employee.management.repo;

import com.employee.management.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Page<Employee> findAllByOrderByDateCreatedDesc(Pageable pageable);

    List<Employee> findAllByIdIn(List<Long> id);

    Optional<Employee> findEmployeeByUuid(String uid);

    Employee findByEmployeeDetails_Id(Long EmployeeDetailsId);

    Employee findRoleByUserName(String username);

    Employee findByUserName(String username);
}
