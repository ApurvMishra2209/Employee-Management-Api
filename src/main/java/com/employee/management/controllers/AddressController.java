package com.employee.management.controllers;


import com.employee.management.model.AddressDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.service.AddressService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {


    private final AddressService addressService;

    public AddressController(final AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<PaginatedResponse<?>> getAllAddress(final Pageable pageable)
    {
        return ResponseEntity.ok(addressService.findAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable final Long id) {
        return ResponseEntity.ok(addressService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createAddress(
            @RequestBody @Valid final AddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.create(addressDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable final Long id,
                                                   @RequestBody @Valid final AddressDTO addressDTO) {
        addressService.update(id, addressDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable final Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
