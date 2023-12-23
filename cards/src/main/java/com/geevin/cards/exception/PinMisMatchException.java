package com.geevin.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PinMisMatchException extends RuntimeException{
    public PinMisMatchException(String message) {super(message);}
}
