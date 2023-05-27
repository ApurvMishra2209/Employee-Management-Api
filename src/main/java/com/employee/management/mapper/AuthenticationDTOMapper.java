package com.employee.management.mapper;

import com.employee.management.domain.Employee;
import com.employee.management.model.AuthenticationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationDTOMapper {

    private final PasswordEncoder passwordEncoder;

    public AuthenticationRequestDTO mapToDTO(final Employee employee, final AuthenticationRequestDTO authenticationRequestDTO) {
        authenticationRequestDTO.setUserName(employee.getUserName());
        authenticationRequestDTO.setPassword(employee.getPassword());
        authenticationRequestDTO.setRole(employee.getRole());
//        authenticationRequestDTO.setUuid(employee.getUuid());
        return authenticationRequestDTO;

    }

    public Employee mapToEntity(final AuthenticationRequestDTO authenticationRequestDTO, final Employee employee) {
        employee.setUserName(authenticationRequestDTO.getUserName());
        employee.setPassword(passwordEncoder.encode(authenticationRequestDTO.getPassword()));
        employee.setRole(authenticationRequestDTO.getRole());
        UUID uuid = UUID.randomUUID();
        employee.setUuid(uuid);
        return employee;
    }
}
