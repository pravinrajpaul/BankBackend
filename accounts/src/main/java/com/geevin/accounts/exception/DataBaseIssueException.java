package com.geevin.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class DataBaseIssueException extends RuntimeException {
    public DataBaseIssueException(String message) {super(message);}
}
