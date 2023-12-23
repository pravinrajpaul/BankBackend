package com.geevin.cards.exception;

import com.geevin.cards.dto.ResponseDTO;
import com.geevin.cards.types.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnknownCardTypeException.class)
    public ResponseEntity<ResponseDTO> unknownCardTypeException(UnknownCardTypeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ResponseDTO(StatusCode.FAILURE, exception.getMessage(), Instant.now()));
    }


    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ResponseDTO> cardNotFoundException(CardNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(StatusCode.FAILURE, exception.getMessage(), Instant.now()));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ResponseDTO> dataBaseException(DataBaseException exception) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ResponseDTO(StatusCode.FAILURE, exception.getMessage(), Instant.now()));
    }

    @ExceptionHandler(PinMisMatchException.class)
    public ResponseEntity<ResponseDTO> pinMisMatchException(PinMisMatchException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseDTO(StatusCode.FAILURE, exception.getMessage(), Instant.now()));
    }

}
