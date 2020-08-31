package com.where2beer.ws.common.exception;

import org.springframework.http.HttpStatus;

public abstract class RestException extends RuntimeException {

    public RestException(RestExceptionEnum restExceptionEnum) {
        super(restExceptionEnum.name());
    }

    public RestException(Throwable throwable) {
        super(throwable);
    }

    public abstract HttpStatus getStatus();
}
