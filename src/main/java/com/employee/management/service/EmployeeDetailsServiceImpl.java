package com.employee.management.service;

import com.employee.management.domain.EmployeeDetails;
import com.employee.management.mapper.EmployeeDetailsDTOMapper;
import com.employee.management.model.EmployeeDetailsDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.repo.EmployeeDetailsRepository;
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
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsDTOMapper employeeDetailsDTOMapper;
    private final EmployeeDetailsRepository employeeDetailsRepository;

    @Override
    public PaginatedResponse<?> findAll(final Pageable pageable) {
        Page<EmployeeDetails> employeeDetailsPage = employeeDetailsRepository.findAllByOrderByDateCreatedDesc(pageable);
        List<EmployeeDetailsDTO> responseData = employeeDetailsPage.stream()
                .map(employeeDetails -> employeeDetailsDTOMapper.mapToDTO(employeeDetails, new EmployeeDetailsDTO()))
                .collect(Collectors.toList());
        return PaginatedResponseUtil.paginatedResponse(responseData, employeeDetailsPage);
    }

    @Override
    public Long create(EmployeeDetailsDTO employeeDetailsDTO) {
        final EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetailsDTOMapper.mapToEntity(employeeDetailsDTO, employeeDetails);
        return employeeDetailsRepository.save(employeeDetails).getId();

    }
    @Override
    public void update(Long id, EmployeeDetailsDTO employeeDetailsDTO) {
        final EmployeeDetails employeeDetails = employeeDetailsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employeeDetailsDTOMapper.mapToEntity(employeeDetailsDTO, employeeDetails);
        employeeDetailsRepository.save(employeeDetails);

    }

    @Override
    public void delete(Long id) { employeeDetailsRepository.deleteById(id); }



    @Override
    public EmployeeDetailsDTO get(Long id) {
        return employeeDetailsRepository.findById(id)
                .map(employeeDetails -> employeeDetailsDTOMapper.mapToDTO(employeeDetails, new EmployeeDetailsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
