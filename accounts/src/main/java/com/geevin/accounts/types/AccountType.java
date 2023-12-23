package com.geevin.accounts.types;

public enum AccountType {
    SAVINGS("Savings"), CURRENT("Current"), FIXED("Fixed"), DEMAT("Demat"), TRADING("Trading");

    public String value;

    private AccountType(String value) {
        this.value = value;
    }
;
}
