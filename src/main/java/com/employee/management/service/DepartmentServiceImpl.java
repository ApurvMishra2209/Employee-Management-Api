package com.employee.management.service;


import com.employee.management.domain.Department;
import com.employee.management.mapper.DepartmentDTOMapper;
import com.employee.management.model.DepartmentDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.repo.DepartmentRepository;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final  DepartmentDTOMapper departmentDTOMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public PaginatedResponse<?> findAll(Pageable pageable) {
        Page<Department> departmentPage = departmentRepository.findAllByOrderByDateCreatedDesc(pageable);
        List<DepartmentDTO> responseData = departmentPage.stream()
                .map(department -> departmentDTOMapper.mapToDTO(department, new DepartmentDTO()))
                .collect(Collectors.toList());
        return PaginatedResponseUtil.paginatedResponse(responseData, departmentPage);
    }

    @Override
    public Long create(DepartmentDTO departmentDTO) {
        final Department department = new Department();
        departmentDTOMapper.mapToEntity(departmentDTO, department);
        return departmentRepository.save(department).getId();

    }



    @Override
    public void update(Long id, DepartmentDTO departmentDTO) {
        final Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        departmentDTOMapper.mapToEntity(departmentDTO, department);
        departmentRepository.save(department);

    }

    @Override
    public void delete(Long id) { departmentRepository.deleteById(id); }



    @Override
    public DepartmentDTO get(Long id) {
        return departmentRepository.findById(id)
                .map(department -> departmentDTOMapper.mapToDTO(department, new DepartmentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
