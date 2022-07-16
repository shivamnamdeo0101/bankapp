package com.shivam.bankapp.repository;

import com.shivam.bankapp.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Override
    List<Account> findAll();
}
