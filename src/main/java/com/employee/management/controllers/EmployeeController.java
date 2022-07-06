package com.employee.management.controllers;


import com.employee.management.model.*;
import com.employee.management.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<PaginatedResponse<?>> getAllEmployees(final Pageable pageable)
    {
        return ResponseEntity.ok(employeeService.findAll(pageable));
    }

    @GetMapping("/getprofile/{uid}")
    public ResponseEntity<ProfileDTO> getEmployeeProfileByUuid(@PathVariable final UUID uid) {
        return ResponseEntity.ok(employeeService.getProfile(uid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getEmployeeProfileById(@PathVariable final Long id,
                                         @RequestHeader("Authorization") final String token) {
        return ResponseEntity.ok(employeeService.get(id , token));
    }

    @PostMapping("/createUser")
    public ResponseEntity<Long> createUser(
            @RequestBody @Valid final AuthenticationRequestDTO authenticationRequestDTO) {
        return new ResponseEntity<>(employeeService.createUser(authenticationRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable final Long id,
                                          @RequestBody @Valid final EmployeeDTO employeeDTO) {
        employeeService.update(id, employeeDTO);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable  final Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch")
        public ResponseEntity<Void> patchUpdateEmployee(@RequestBody @Valid final EmployeePatchDTO employeePatchDTO){
        employeeService.patchUpdateEmployee(employeePatchDTO);
        return ResponseEntity.ok().build();
    }


}
