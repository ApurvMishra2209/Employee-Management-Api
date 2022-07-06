package com.employee.management.service;

import com.employee.management.domain.SkillSet;
import com.employee.management.mapper.SkillSetDTOMapper;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.model.ProfileSkillsetDTO;
import com.employee.management.model.SkillSetDTO;
import com.employee.management.repo.SkillSetRepository;
import com.employee.management.util.PaginatedResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SkillSetServiceImpl implements SkillSetService{

    private final SkillSetDTOMapper skillSetDTOMapper;
    private final SkillSetRepository skillSetRepository;

    @Override
    public PaginatedResponse<?> findAll(final Pageable pageable) {
        Page<SkillSet> skillSetPage = skillSetRepository.findAllByOrderByDateCreatedDesc(pageable);
        List<ProfileSkillsetDTO> responseData = skillSetPage.stream()
                .map(skillSet -> skillSetDTOMapper.mapToProfile(skillSet, new ProfileSkillsetDTO()))
                .collect(Collectors.toList());
        return PaginatedResponseUtil.paginatedResponse(responseData, skillSetPage);
    }

    @Override
    public Long create(SkillSetDTO skillSetDTO) {
        final SkillSet skillSet = new SkillSet();
        skillSetDTOMapper.mapToEntity(skillSetDTO, skillSet);
        return skillSetRepository.save(skillSet).getId();
    }



    @Override
    public void update(Long id, SkillSetDTO skillSetDTO) {
        final SkillSet skillSet = skillSetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        skillSetDTOMapper.mapToEntity(skillSetDTO, skillSet);
        skillSetRepository.save(skillSet);

    }

    @Override
    public void delete(Long id) { skillSetRepository.deleteById(id); }



    @Override
    public ProfileSkillsetDTO get(Long id) {
        return skillSetRepository.findById(id)
                .map(skillSet -> skillSetDTOMapper.mapToProfile(skillSet, new ProfileSkillsetDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
