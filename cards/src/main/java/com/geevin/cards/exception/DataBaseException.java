package com.geevin.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class DataBaseException extends RuntimeException{
    public DataBaseException(String message) {
        super(message);
    }
}
