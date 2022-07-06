package com.employee.management.controllers;

import com.employee.management.model.EmployeeDetailsDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.service.EmployeeDetailsService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/employeedetails", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeDetailsController {

    private final EmployeeDetailsService employeeDetailsService;

    public EmployeeDetailsController(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<PaginatedResponse<?>> getAllEmployeeDetails(final Pageable pageable)
    {
        return ResponseEntity.ok(employeeDetailsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailsDTO> getemployeeDetails(@PathVariable final Long id) {
        return ResponseEntity.ok(employeeDetailsService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createEmployeeDetails(
            @RequestBody @Valid final EmployeeDetailsDTO employeeDetailsDTO) {
        return new ResponseEntity<>(employeeDetailsService.create(employeeDetailsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployeeDetails(@PathVariable final Long id,
                                               @RequestBody @Valid final EmployeeDetailsDTO employeeDetailsDTO) {
        employeeDetailsService.update(id,employeeDetailsDTO );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeDetails(@PathVariable final Long id) {
        employeeDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
