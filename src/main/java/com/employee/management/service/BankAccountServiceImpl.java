package com.employee.management.service;

import com.employee.management.domain.BankAccount;
import com.employee.management.mapper.BankAccountDTOMapper;
import com.employee.management.model.BankAccountDTO;
import com.employee.management.model.PaginatedResponse;
import com.employee.management.repo.BankAccountRepository;
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
public class BankAccountServiceImpl implements BankAccountService{

     private final  BankAccountDTOMapper bankAccountDTOMapper;
     private final BankAccountRepository bankAccountRepository;


    @Override
    public PaginatedResponse<?> findAll(final Pageable pageable) {
        Page<BankAccount> bankAccountPage = bankAccountRepository.findAllByOrderByDateCreatedDesc(pageable);
        List<BankAccountDTO> responseData = bankAccountPage.stream()
                .map(address -> bankAccountDTOMapper.mapToDTO(address, new BankAccountDTO()))
                .collect(Collectors.toList());
        return PaginatedResponseUtil.paginatedResponse(responseData, bankAccountPage);
    }

    @Override
    public Long create(BankAccountDTO bankAccountDTO) {
        final BankAccount bankAccount = new BankAccount();
        bankAccountDTOMapper.mapToEntity(bankAccountDTO, bankAccount);
        return bankAccountRepository.save(bankAccount).getId();
    }



    @Override
    public void update(Long id, BankAccountDTO bankAccountDTO) {
        final BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        bankAccountDTOMapper.mapToEntity(bankAccountDTO, bankAccount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void delete(Long id) { bankAccountRepository.deleteById(id); }



    @Override
    public BankAccountDTO get(Long id) {
        return bankAccountRepository.findById(id)
                .map(bankAccount -> bankAccountDTOMapper.mapToDTO(bankAccount, new BankAccountDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
