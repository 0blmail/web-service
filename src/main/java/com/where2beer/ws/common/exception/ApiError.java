package com.where2beer.ws.common.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiError {
    private final Long timestamp = Instant.now().toEpochMilli();
    private int status;
    private String error;
    private String message;
    private String stack;
}
