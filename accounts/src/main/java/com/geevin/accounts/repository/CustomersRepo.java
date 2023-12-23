package com.geevin.accounts.repository;

import com.geevin.accounts.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepo extends JpaRepository<CustomersEntity, Long> {

    Optional<CustomersEntity> findByMobileNumber(String mobileNumber);

}