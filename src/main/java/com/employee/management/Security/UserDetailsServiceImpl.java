package com.employee.management.Security;

import com.employee.management.domain.Employee;
import com.employee.management.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee= employeeRepository.findByUserName(username);
        if(employee==null){
            throw new UsernameNotFoundException("User not found");
        }
        return  new MyUserDetails(employee);
    }
}
