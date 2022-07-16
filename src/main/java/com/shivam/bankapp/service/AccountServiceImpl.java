package com.shivam.bankapp.service;

import com.shivam.bankapp.domain.Account;
import com.shivam.bankapp.dto.AccountDetailsDto;
import com.shivam.bankapp.dto.AccountDto;
import com.shivam.bankapp.exception.*;
import com.shivam.bankapp.repository.AccountRepository;
import com.shivam.bankapp.util.EntityDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository repository;

    @Override
    public void createAccount(AccountDto dto) {
        boolean pass_valid = dto.getPassword().equals("12345");

        if (!(pass_valid)) {
            throw new AdminPermitException("Admin Permission Required");
        }else{
            repository.save(EntityDtoConversion.toEntity(dto));
        }

    }

    @Override
    public List<AccountDto> showAllAccounts(AccountDetailsDto dto) {

        boolean pass_valid = dto.getPassword().equals("12345");

        if(!(pass_valid) ){
            throw new AdminPermitException("Admin Permission Required");
        }else{
            return repository.findAll()
                    .stream()
                    .map(EntityDtoConversion::toDto)
                    .collect(Collectors.toList());
        }

    }


    @Override
    public double withdraw(Long id, Double amount) {

        if (amount <= 0) throw new InvalidAmountException("Amount must positive");

        Optional<Account> optional = repository.findById(id);
        Account account = optional.orElseThrow(() -> new AccountNotFoundException("Account with " + id + " not found"));

        Double currentBalance = account.getBalance();

        if(!account.getIsActive()) throw new AccountNotActiveException("Account Is Not Active");
        if(account.getIsBlocked()) throw new AccountBlockedException("Account Is Blocked");
        if (currentBalance <= 0) throw new InvalidAmountException("Can not withdraw as your balance is zero");
        if (currentBalance <= amount) throw new InvalidAmountException("Amount can not be greater then your balance");


        Double newBalance = currentBalance - amount;
        account.setBalance(newBalance);

        return amount;
    }

    @Override
    public int toogleActive(Long id, boolean status,String password) {

        Optional<Account> optional = repository.findById(id);
        Account account = optional.orElseThrow(() -> new AccountNotFoundException("Account with " + id + " not found"));

        boolean pass_valid = password.equals("12345");

        if(!(pass_valid)){
            throw new AdminPermitException("Admin Permission Required");
        }else{
            account.setIsActive(!account.getIsActive());
            return 1;
        }


    }
    @Override
    public int toogleBlock(Long id, boolean status,String password) {

        Optional<Account> optional = repository.findById(id);
        Account account = optional.orElseThrow(() -> new AccountNotFoundException("Account with " + id + " not found"));
        boolean pass_valid = password.equals("12345");

        if(!(pass_valid) ){
            throw new AdminPermitException("Admin Permission Required");
        }else{
            account.setIsBlocked(!account.getIsBlocked());
            return 1;
        }

    }

    @Override
    public double deposit(Long id, Double amount) {
        if (amount <= 0) throw new InvalidAmountException("Amount must positive");

        Optional<Account> optional = repository.findById(id);
        Account account = optional.orElseThrow(() -> new AccountNotFoundException("Account with " + id + " not found"));

        if(!account.getIsActive()) throw new AccountNotActiveException("Account Is Not Active");
        if(account.getIsBlocked()) throw new AccountBlockedException("Account Is Blocked");


        Double currentBalance = account.getBalance();
        Double newBalance = currentBalance + amount;
        account.setBalance(newBalance);

        return amount;
    }

    @Override
    public double transfer(Long src, Long dst, Double amount) {

        if (amount <= 0) throw new InvalidAmountException("Amount must positive");
        withdraw(src, amount);
        deposit(dst, amount);
        return amount;
    }
}
