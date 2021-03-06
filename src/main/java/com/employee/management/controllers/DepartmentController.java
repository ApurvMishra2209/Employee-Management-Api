package com.employee.management.controllers;

import com.employee.management.model.DepartmentDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.service.DepartmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/department", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<PaginatedResponse<?>> getAllDepartment(final Pageable pageable)

    {
        return ResponseEntity.ok(departmentService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable final Long id) {
        return ResponseEntity.ok(departmentService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createDepartment(
            @RequestBody @Valid final DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.create(departmentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable final Long id,
                                               @RequestBody @Valid final DepartmentDTO departmentDTO) {
        departmentService.update(id, departmentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable final Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
