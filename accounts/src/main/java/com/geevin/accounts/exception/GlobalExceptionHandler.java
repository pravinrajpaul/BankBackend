package com.geevin.accounts.exception;

import com.geevin.accounts.dto.ResponseDTO;
import com.geevin.accounts.types.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExists.class)
    public ResponseEntity<ResponseDTO> throwCustomerAlreadyExists(CustomerAlreadyExists exception) {
        ResponseDTO response = new ResponseDTO(StatusCode.FAILURE, exception.getMessage(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<ResponseDTO> throwCustomerNotFound(CustomerNotFound exception) {
        ResponseDTO response = new ResponseDTO(StatusCode.FAILURE, exception.getMessage(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataBaseIssueException.class)
    public ResponseEntity<ResponseDTO> throwDataBaseIssueException(DataBaseIssueException exception) {
        ResponseDTO response = new ResponseDTO(StatusCode.ERROR, exception.getMessage(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> throwInvalidCustomerDTO(MethodArgumentNotValidException exception) {
        List<ObjectError> errorsObj = exception.getBindingResult().getAllErrors();
        List<Map<String, String>> errors = new ArrayList<>();
        errorsObj.forEach((error) -> {
            String errorField = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            Map<String, String> errorMap  = new HashMap<>();
            errorMap.put(errorField, errorMessage);
            errors.add(errorMap);
        });
        ResponseDTO response = new ResponseDTO(StatusCode.FAILURE, errors, Instant.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
