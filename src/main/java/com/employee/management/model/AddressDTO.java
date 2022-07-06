package com.employee.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class AddressDTO {

    private Long id;

    private String addressType;

    private String street;

    private String postalCode;

    private String city;

    private String country;

    private Long employee;

    private String state;
}
