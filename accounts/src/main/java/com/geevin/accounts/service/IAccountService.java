package com.geevin.accounts.service;

import com.geevin.accounts.dto.CustomersDTO;
import com.geevin.accounts.entity.AccountsEntity;

public interface IAccountService {
    Long createAccount(CustomersDTO customerDTO);
    AccountsEntity getAccount(String mobileNo);
    Boolean updateAccount(Long accNo, CustomersDTO cdto);
    Boolean deleteAccount(Long accNo);
}
