package com.where2beer.ws.common.security.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.where2beer.ws.common.exception.ApiError;
import com.where2beer.ws.common.exception.RestExceptionEnum;
import com.where2beer.ws.common.exception.custom.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class ErrorResponseHelper {

    private final ObjectMapper objectMapper;

    public void send(HttpServletResponse response, RestExceptionEnum restExceptionEnum) throws IOException {
        var exception = new ForbiddenException(restExceptionEnum);
        var error = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(exception.getMessage())
                .build();

        response.setStatus(error.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().println(objectMapper.writeValueAsString(error));
        response.getWriter().flush();
    }
}
