package com.employee.management.service;


import com.employee.management.domain.Address;
import com.employee.management.mapper.AddressDTOMapper;
import com.employee.management.model.AddressDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.repo.AddressRepository;
import com.employee.management.util.PaginatedResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressDTOMapper addressDTOMapper;
    private final AddressRepository addressRepository;

    @Override
    public PaginatedResponse<?> findAll(final Pageable pageable) {
        Page<Address> addressPage = addressRepository.findAllByOrderByDateCreatedDesc(pageable);
        List<AddressDTO> responseData = addressPage.stream()
                .map(address -> addressDTOMapper.mapToDTO(address, new AddressDTO()))
                .collect(Collectors.toList());
        return PaginatedResponseUtil.paginatedResponse(responseData, addressPage);
    }

    @Override
    public Long create(AddressDTO addressDTO) {
        final Address address = new Address();
        addressDTOMapper.mapToEntity(addressDTO, address);
        return addressRepository.save(address).getId();
    }



    @Override
    public void update(Long id, AddressDTO addressDTO) {
        final Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        addressDTOMapper.mapToEntity(addressDTO, address);
        addressRepository.save(address);

    }

    @Override
    public void delete(Long id) { addressRepository.deleteById(id); }



    @Override
    public AddressDTO get(Long id) {
        return addressRepository.findById(id)
                .map(address -> addressDTOMapper.mapToDTO(address, new AddressDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
