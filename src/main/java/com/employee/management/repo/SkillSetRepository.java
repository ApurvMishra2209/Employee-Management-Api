package com.employee.management.repo;

import com.employee.management.domain.SkillSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SkillSetRepository extends JpaRepository<SkillSet,Long> {

    Page<SkillSet> findAllByOrderByDateCreatedDesc(Pageable pageable);

    List<SkillSet> findAllBySkillNameIn(Set<String> skillName);
}
