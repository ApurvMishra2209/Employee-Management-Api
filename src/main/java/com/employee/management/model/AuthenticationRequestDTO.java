package com.employee.management.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationRequestDTO {

    @ApiModelProperty(position = 1)
    @NotNull
    private String userName;

    @ApiModelProperty(position = 2)
    @NotNull
    private String password;

    @ApiModelProperty(position = 3)
    @NotNull
    private Role role;


}
