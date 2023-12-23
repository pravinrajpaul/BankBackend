package com.geevin.accounts.types;

public enum StatusCode {

    SUCCESS("Success"), FAILURE("Failure"), PROGRESS("In Progress"), ERROR("Error");

    public final String value;

    private StatusCode(String value) {
        this.value = value;
    }
}