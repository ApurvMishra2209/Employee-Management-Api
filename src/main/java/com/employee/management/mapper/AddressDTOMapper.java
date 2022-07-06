package com.employee.management.mapper;

import com.employee.management.domain.Address;
import com.employee.management.domain.Employee;
import com.employee.management.model.AddressDTO;
import com.employee.management.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class AddressDTOMapper {

    private final EmployeeRepository employeeRepository;

    public AddressDTO mapToDTO(final Address address,final AddressDTO addressDTO)
    {
        addressDTO.setAddressType(address.getAddressType());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setId(address.getId());
        addressDTO.setState(address.getState());
        addressDTO.setEmployee(address.getEmployee() == null ? null : address.getEmployee().getId());
        return addressDTO;

    }

    public Address mapToEntity(final AddressDTO addressDTO,final Address address)
    {
        address.setAddressType(addressDTO.getAddressType());
        address.setCity(addressDTO.getCity());
        address.setId(addressDTO.getId());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        if(addressDTO.getEmployee() != null
        && (address.getEmployee() == null ||
                !address.getEmployee().getId().equals(addressDTO.getEmployee())))
        {

           final Employee employee=employeeRepository.findById(addressDTO.getEmployee())
                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found"));
            address.setEmployee(employee);

        }
        return address;

    }
}
