package com.shivam.bankapp.service;
import com.shivam.bankapp.domain.Account;
import com.shivam.bankapp.dto.AccountDetailsDto;
import com.shivam.bankapp.dto.AccountDto;

import javax.validation.Valid;
import java.util.List;

public interface AccountService {
    void createAccount(AccountDto account);
    List<AccountDto> showAllAccounts(AccountDetailsDto dto);
    double withdraw(Long id, Double amount);
    double deposit(Long id, Double amount);
    double transfer(Long src, Long dst, Double amount);
    int toogleBlock(Long src,boolean status,String pass);
    int toogleActive(Long src,boolean status,String pass);

}
