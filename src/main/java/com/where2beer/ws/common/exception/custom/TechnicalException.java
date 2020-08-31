package com.where2beer.ws.common.exception.custom;

import com.where2beer.ws.common.exception.RestException;
import com.where2beer.ws.common.exception.RestExceptionEnum;
import org.springframework.http.HttpStatus;

public class TechnicalException extends RestException {

    public TechnicalException(RestExceptionEnum restExceptionEnum) {
        super(restExceptionEnum);
    }

    public TechnicalException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
