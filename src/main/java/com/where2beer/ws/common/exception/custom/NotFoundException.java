package com.where2beer.ws.common.exception.custom;

import com.where2beer.ws.common.exception.RestException;
import com.where2beer.ws.common.exception.RestExceptionEnum;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {

    public NotFoundException() {
        super(RestExceptionEnum.RESOURCE_NOT_FOUND);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
