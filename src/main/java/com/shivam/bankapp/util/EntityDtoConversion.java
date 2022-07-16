package com.shivam.bankapp.util;

import com.shivam.bankapp.domain.Account;
import com.shivam.bankapp.dto.AccountDto;

public class EntityDtoConversion {

    public static Account toEntity(AccountDto dto) {
        return new Account(
                dto.getId(),
                dto.getName(),
                dto.getPassword(),
                dto.getBalance(),
                dto.getIsAdmin(),
                dto.getIsBlocked(),
                dto.getIsActive()
        );
    }

    public static AccountDto toDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getName(),
                account.getPassword(),
                account.getBalance(),
                account.getIsAdmin(),
                account.getIsBlocked(),
                account.getIsActive()
        );
    }
}
