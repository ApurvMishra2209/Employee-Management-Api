package com.employee.management.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse
{
    String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
