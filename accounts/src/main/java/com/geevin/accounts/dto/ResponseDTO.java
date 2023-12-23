package com.geevin.accounts.dto;

import com.geevin.accounts.types.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ResponseDTO {
    private StatusCode statusCode;
    private Object statusMessage;
    private Instant timeStamp;
}