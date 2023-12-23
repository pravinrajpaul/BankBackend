package com.geevin.accounts.mapper;

import com.geevin.accounts.dto.AccountsDTO;
import com.geevin.accounts.entity.AccountsEntity;

import java.time.Instant;

public class AccountMapper {

    public static AccountsEntity toAccountsEntity(AccountsDTO adto, AccountsEntity ace) {
        ace.setAccountNumber(adto.getAccountNumber());
        ace.setAccountType(adto.getAccountType());
        ace.setIfscCode(adto.getIfscCode());
        ace.setCustomerId(null);
        ace.setCreatedAt(Instant.now());
        ace.setCreatedBy("Dummy");
        ace.setUpdatedAt(Instant.now());
        ace.setUpdatedBy("Dummy");
        return ace;
    }

    public static AccountsDTO toAccountsDTO(AccountsDTO adto, AccountsEntity ace) {
        adto.setAccountType(ace.getAccountType());
        adto.setAccountNumber(ace.getAccountNumber());
        adto.setIfscCode(ace.getIfscCode());
        return adto;
    }
}
