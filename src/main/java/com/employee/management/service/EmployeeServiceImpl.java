package com.employee.management.service;

import com.employee.management.domain.Employee;

import com.employee.management.exception.NotFoundException;
import com.employee.management.mapper.AuthenticationDTOMapper;
import com.employee.management.mapper.EmployeeDTOMapper;
import com.employee.management.mapper.EmployeePatchDTOMapper;
import com.employee.management.mapper.ProfileDTOMapper;
import com.employee.management.model.*;
import com.employee.management.repo.EmployeeRepository;
import com.employee.management.util.JwtUtils;
import com.employee.management.util.PaginatedResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeePatchDTOMapper employeePatchDTOMapper;
    private final ProfileDTOMapper profileDTOMapper;
    private final AuthenticationDTOMapper authenticationDTOMapper;

    @Override
    public Long createUser(AuthenticationRequestDTO authenticationRequestDTO) {
            final Employee employee = new Employee();
            authenticationDTOMapper.mapToEntity(authenticationRequestDTO, employee);
            return employeeRepository.save(employee).getId();

        }

    @Override
    public ProfileDTO getProfile(final String uid) {
        return employeeRepository.findEmployeeByUuid(uid)
                .map(employee -> profileDTOMapper.mapToProfile(employee,new ProfileDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));
    }


    @Override
    public PaginatedResponse<?> findAll(final Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAllByOrderByDateCreatedDesc(pageable);
        List<ProfileDTO> responseData = employeePage.stream()
                .map(employee -> profileDTOMapper.mapToProfile(employee, new ProfileDTO()))
                .collect(Collectors.toList());
        return PaginatedResponseUtil.paginatedResponse(responseData, employeePage);
    }

    @Override
    public ProfileDTO get(final Long id, final String token) {
        if (!JwtUtils.getRole(token).equals(Role.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Only admin can access");
        }
        return employeeRepository.findById(id)
                .map(employee -> profileDTOMapper.mapToProfile(employee, new ProfileDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void update(Long id, EmployeeDTO employeeDTO) {
        final Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            employeeDTOMapper.mapToEntity(employeeDTO, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void delete(final Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void patchUpdateEmployee( Long id,EmployeePatchDTO employeePatchDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with ID: " + id));
        employeePatchDTOMapper.mapToEntity(employeePatchDTO,employee);
        employeeRepository.save(employee);
    }
}
