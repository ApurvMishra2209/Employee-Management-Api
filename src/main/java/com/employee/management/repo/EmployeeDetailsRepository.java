package com.employee.management.repo;


import com.employee.management.domain.EmployeeDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Long> {

    Page<EmployeeDetails> findAllByOrderByDateCreatedDesc(Pageable pageable);
}
