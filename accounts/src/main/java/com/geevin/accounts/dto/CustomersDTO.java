package com.geevin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Holds customer information"
)
@Data
public class CustomersDTO {

    @Schema(
            description = "Holds name of the customer",
            examples = {"Pravin", "Raj Paul", "Pravin 1", "Mr. Pravin1"}
    )
    @NotEmpty (message = "Name should not be null or empty")
    @Size(min = 3, message = "Name should have minimum 3 characters")
    @Size(max = 100, message = "Name should not have more than 5 characters")
    @Pattern.List ({
            @Pattern(regexp = "^[^0-9\\Q!@#$%&^*><,/\\.?'\";:()_=+-[]{}`~\\E].*$", message = "Should not start with digit or special character"),
            @Pattern(regexp = "^[A-Za-z\\-\\d\\s'\\\\]*$", message = "Should not contain special characters")
    })
    private String name;

    @Email(message = "Email format is invalid")
    private String email;

    @NotEmpty (message = "Mobile number should not be null or empty")
    @Digits(message = "Only digits are allowed for the mobile number", integer = 10, fraction = 0)
    @Size(min = 10, max = 10, message = "Size of mobile number should be 10 digits")
    private String mobileNumber;
}