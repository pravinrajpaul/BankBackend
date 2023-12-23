package com.geevin.accounts.mapper;

import com.geevin.accounts.dto.CustomersDTO;
import com.geevin.accounts.entity.CustomersEntity;
import com.geevin.accounts.repository.CustomersRepo;

import java.time.Instant;

public class CustomerMapper {

    public static CustomersEntity toCustomersEntity (CustomersDTO cdto, CustomersEntity cse) {
        cse.setEmail(cdto.getEmail());
        cse.setName(cdto.getName());
        cse.setMobileNumber(cdto.getMobileNumber());
        cse.setCreatedAt(Instant.now());
        cse.setCreatedBy("Dummy");
        cse.setUpdatedAt(Instant.now());
        cse.setUpdatedBy("Dummy");
        return cse;
    }

    public static CustomersDTO toCustomersDTO (CustomersDTO cdto, CustomersEntity cse) {
        cdto.setEmail(cse.getEmail());
        cdto.setName(cse.getName());
        cdto.setMobileNumber(cse.getMobileNumber());
        return cdto;
    }

}
