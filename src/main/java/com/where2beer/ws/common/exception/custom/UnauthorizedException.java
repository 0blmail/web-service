package com.where2beer.ws.common.exception.custom;

import com.where2beer.ws.common.exception.RestException;
import com.where2beer.ws.common.exception.RestExceptionEnum;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RestException {

    public UnauthorizedException(RestExceptionEnum restExceptionEnum) {
        super(restExceptionEnum);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
