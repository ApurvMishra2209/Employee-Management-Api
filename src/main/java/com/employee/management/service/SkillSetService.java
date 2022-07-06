package com.employee.management.service;

import com.employee.management.model.PaginatedResponse;
import com.employee.management.model.ProfileSkillsetDTO;
import com.employee.management.model.SkillSetDTO;
import org.springframework.data.domain.Pageable;

public interface SkillSetService {

    PaginatedResponse<?> findAll(final Pageable pageable);

    Long create( final SkillSetDTO skillSetDTO);

    void update(final Long id, final SkillSetDTO skillSetDTO);

    void delete(final Long id);

    ProfileSkillsetDTO get(final Long id);
}
