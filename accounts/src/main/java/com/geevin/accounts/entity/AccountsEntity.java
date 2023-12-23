package com.geevin.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class AccountsEntity extends CreateDetailsEntity {

    private Long customerId;
    @Id
    private Long accountNumber;
    private String accountType;
    private String ifscCode;
}