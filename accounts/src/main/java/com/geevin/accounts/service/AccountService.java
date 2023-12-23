package com.geevin.accounts.service;

import com.geevin.accounts.dto.CustomersDTO;
import com.geevin.accounts.entity.AccountsEntity;
import com.geevin.accounts.entity.CustomersEntity;
import com.geevin.accounts.exception.CustomerAlreadyExists;
import com.geevin.accounts.exception.CustomerNotFound;
import com.geevin.accounts.exception.DataBaseIssueException;
import com.geevin.accounts.mapper.CustomerMapper;
import com.geevin.accounts.repository.AccountsRepo;
import com.geevin.accounts.repository.CustomersRepo;
import com.geevin.accounts.types.AccountType;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private AccountsRepo accountsRepo;
    private CustomersRepo customersRepo;


    @Override
    public Long createAccount(CustomersDTO customerDTO) {

        CustomersEntity cse = CustomerMapper.toCustomersEntity(customerDTO, new CustomersEntity());
        String mob = cse.getMobileNumber();
        Optional<CustomersEntity> csem = customersRepo.findByMobileNumber(mob);
        if(csem.isPresent()) throw new CustomerAlreadyExists("Customer with mobile number "+mob+" already exists.");
        CustomersEntity scse = customersRepo.save(cse);

        Long accountNumber = new Random().nextLong(1000000000L);
        String ifscCode = RandomStringUtils.randomAlphabetic(4).toUpperCase() + RandomUtils.nextLong(100001, 1000000);
        int len = AccountType.values().length;
        String accountType = AccountType.values()[RandomUtils.nextInt(0,len)].value;
        AccountsEntity ase = new AccountsEntity(scse.getCustomerId(), accountNumber, accountType, ifscCode);
        AccountsEntity sase = accountsRepo.save(ase);
        return sase.getAccountNumber();
    }

    @Override
    public AccountsEntity getAccount(String mobileNo) {

        CustomersEntity csem = customersRepo.findByMobileNumber(mobileNo).orElseThrow(()->new CustomerNotFound("Customer with mobile number "+mobileNo+" does not exist."));
        Long cid = csem.getCustomerId();

        AccountsEntity ase = accountsRepo.findFirstByCustomerId(cid).orElseThrow(()->new CustomerNotFound("Customer with mobile number "+mobileNo+" does not exist."));
        return ase;
    }

    @Override
    public Boolean updateAccount(Long accNo, CustomersDTO cdto) {
        AccountsEntity ase = accountsRepo.findById(accNo).orElseThrow(()->new CustomerNotFound("Customer with account number "+accNo+" does not exist."));
        Long cid = ase.getCustomerId();
        CustomersEntity cse = CustomerMapper.toCustomersEntity(cdto, new CustomersEntity());
        cse.setCustomerId(cid);
        try{
            CustomersEntity scse = customersRepo.save(cse);
        }
        catch (Exception e) {
            throw new DataBaseIssueException("Database write exception : " + e.getMessage());
        }

        return true;
    }

    @Override
    public Boolean deleteAccount(Long accNo) {
        AccountsEntity ace = accountsRepo.findById(accNo).orElseThrow(()->new CustomerNotFound("Customer with account number "+accNo+" does not exist."));
        Long cid = ace.getCustomerId();
        try{
            customersRepo.deleteById(cid);
            accountsRepo.deleteById(accNo);
        }
        catch (Exception e) {
            throw new DataBaseIssueException("Database write exception : " + e.getMessage());
        }
        return true;
    }

}
