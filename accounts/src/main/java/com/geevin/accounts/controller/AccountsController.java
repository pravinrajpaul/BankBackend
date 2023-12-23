package com.geevin.accounts.controller;

import com.geevin.accounts.dto.AccountsDTO;
import com.geevin.accounts.dto.CustomersDTO;
import com.geevin.accounts.dto.ResponseDTO;
import com.geevin.accounts.entity.AccountsEntity;
import com.geevin.accounts.mapper.AccountMapper;
import com.geevin.accounts.service.IAccountService;
import com.geevin.accounts.types.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Tag(
        name = "Account Services",
        description = "All CRUD Account Services"
)
@RestController
@RequestMapping(path = "${application.basePath}", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private IAccountService iAccountService;

    @Operation(
            summary = "Create a new account",
            description = "Creates a new account and returns the account number"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created account successfully"
    )
    @PostMapping(path = "createAccount", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomersDTO customerDTO) {

        Long accNo = iAccountService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(StatusCode.SUCCESS, "Account " + accNo + " created successfully.", Instant.now()));
    }

    @Operation(
            summary = "Update account info",
            description = "Update account details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created account successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
//                    content = @Content(
//                            schema = @Schema(implementation = )
//                    )
            )
    })
    @PutMapping(path = "updateAccountDetails/{accNo}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomersDTO customerDTO, @PathVariable Long accNo) {

        iAccountService.updateAccount(accNo, customerDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(StatusCode.SUCCESS, "Account " + accNo + " updated successfully.", Instant.now()));
    }

    @DeleteMapping(path = "deleteAccount/{accNo}")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@PathVariable Long accNo) {

        iAccountService.deleteAccount(accNo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(StatusCode.SUCCESS, "Account " + accNo + " deleted successfully.", Instant.now()));
    }


    @GetMapping(path = "getAccount/{mobileNo}")
    public ResponseEntity<AccountsDTO> getAccount(@PathVariable String mobileNo) {

        AccountsEntity ase = iAccountService.getAccount(mobileNo);
        AccountsDTO adto = AccountMapper.toAccountsDTO(new AccountsDTO(), ase);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adto);
    }


    @GetMapping(path = "ping")
    public ResponseEntity<ResponseDTO> ping() {
        ResponseDTO response = new ResponseDTO(StatusCode.SUCCESS, "Ping successful", Instant.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
