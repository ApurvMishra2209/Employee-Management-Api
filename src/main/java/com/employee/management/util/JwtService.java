package com.employee.management.util;

import com.employee.management.Security.UserDetailsServiceImpl;
import com.employee.management.domain.Employee;
import com.employee.management.model.JwtRequest;
import com.employee.management.model.JwtResponse;
import com.employee.management.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;
    private final EmployeeRepository employeeRepository;

    public JwtResponse createToken(final JwtRequest jwtRequest) throws Exception {
        try
        {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken
                            (jwtRequest.getUsername(),jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        catch (BadCredentialsException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        Employee employee=employeeRepository.findRoleByUserName(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(employee);
        return new JwtResponse(token);
    }
    }

