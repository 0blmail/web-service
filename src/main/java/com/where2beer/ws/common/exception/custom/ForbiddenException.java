package com.where2beer.ws.common.exception.custom;

import com.where2beer.ws.common.exception.RestException;
import com.where2beer.ws.common.exception.RestExceptionEnum;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends RestException {

    public ForbiddenException(RestExceptionEnum restExceptionEnum) {
        super(restExceptionEnum);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
