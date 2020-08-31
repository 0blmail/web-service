package com.where2beer.ws.common.exception;

import com.where2beer.ws.common.exception.custom.TechnicalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestException.class)
    protected ResponseEntity<ApiError> handleRestException(RestException restException) {
        return this.buildResponseEntity(restException);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<ApiError> handleThrowable(Throwable throwable) {
        var restException = new TechnicalException(throwable);

        return this.buildResponseEntity(restException);
    }

    private ResponseEntity<ApiError> buildResponseEntity(RestException restException) {
        var error = ApiError.builder()
                .status(restException.getStatus().value())
                .error(restException.getStatus().getReasonPhrase())
                .message(restException.getMessage())
                .build();

        return new ResponseEntity<>(error, restException.getStatus());
    }
}
