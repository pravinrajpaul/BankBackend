package com.geevin.cards.dto;

import com.geevin.cards.types.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data @AllArgsConstructor
public class ResponseDTO {
    private StatusCode statusCode;
    private Object statusMessage;
    private Instant timeStamp;
}