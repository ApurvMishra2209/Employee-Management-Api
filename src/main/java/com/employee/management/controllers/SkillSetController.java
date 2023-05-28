package com.employee.management.controllers;

import com.employee.management.exception.AlreadyExistsException;
import com.employee.management.exception.ErrorResponse;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.model.ProfileSkillsetDTO;
import com.employee.management.model.SkillSetDTO;
import com.employee.management.service.SkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/skillset", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillSetController {

    private final SkillSetService skillSetService;

    @GetMapping("/getAll")
    public ResponseEntity<PaginatedResponse<?>> getAllSkillSet(final Pageable pageable)
    {
        return ResponseEntity.ok(skillSetService.findAll(pageable));
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProfileSkillsetDTO> getSkill(@PathVariable final Long id) {
        return ResponseEntity.ok(skillSetService.get(id));
    }

    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody @Valid final SkillSetDTO skillSetDTO) {
        try {
            Long skillId = skillSetService.create(skillSetDTO);
            return new ResponseEntity<>(skillId, HttpStatus.CREATED);
        } catch (AlreadyExistsException ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSkill(@PathVariable final Long id,
                                               @RequestBody @Valid final SkillSetDTO skillSetDTO) {
        skillSetService.update(id, skillSetDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable final Long id) {
        skillSetService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
