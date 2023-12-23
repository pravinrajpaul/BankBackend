package com.geevin.accounts.repository;

import com.geevin.accounts.controller.AccountsController;
import com.geevin.accounts.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepo extends JpaRepository<AccountsEntity, Long> {

    Optional<AccountsEntity> findFirstByCustomerId(Long customerId);
}
