package com.geevin.accounts.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class AccountsDTO {

    private Long accountNumber;
    private String accountType;
    private String ifscCode;
}
