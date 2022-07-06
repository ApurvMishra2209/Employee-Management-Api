package com.employee.management.controllers;


import com.employee.management.model.JwtRequest;
import com.employee.management.model.JwtResponse;
import com.employee.management.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;;

    @PostMapping
    public ResponseEntity<JwtResponse> generateToken(@RequestBody @Valid final JwtRequest jwtRequest) throws Exception {
        return ResponseEntity.ok(jwtService.createToken(jwtRequest));
    }
}
