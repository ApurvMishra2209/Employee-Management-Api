package com.employee.management.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationRequestDTO {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private Role role;

    private UUID uuid;

}
