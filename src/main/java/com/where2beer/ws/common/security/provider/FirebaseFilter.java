package com.where2beer.ws.common.security.provider;

import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.where2beer.ws.common.exception.RestExceptionEnum;
import com.where2beer.ws.common.security.helper.ErrorResponseHelper;
import com.where2beer.ws.common.security.model.FirebaseAuthenticationToken;
import com.where2beer.ws.common.security.model.FirebaseTokenHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseFilter extends AbstractAuthenticationProcessingFilter {

    private static final String TOKEN_HEADER = "x-where2beer-token";
    private final ErrorResponseHelper errorResponseHelper;

    public FirebaseFilter(ErrorResponseHelper errorResponseHelper) {
        super("**");
        this.errorResponseHelper = errorResponseHelper;
        this.setAuthenticationSuccessHandler((request, response, authentication) -> {
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authToken = request.getHeader(TOKEN_HEADER);
        if (Strings.isNullOrEmpty(authToken)) {
            errorResponseHelper.send(response, RestExceptionEnum.NO_TOKEN_PROVIDED);
            return null;
        }

        try {
            var firebaseToken = FirebaseAuth.getInstance().verifyIdToken(authToken);
            var holder = FirebaseTokenHolder.builder()
                    .uid(firebaseToken.getUid())
                    .email(firebaseToken.getEmail())
                    .name(firebaseToken.getName())
                    .claims(firebaseToken.getClaims())
                    .build();
            return new FirebaseAuthenticationToken(firebaseToken.getUid(), holder);
        } catch (FirebaseAuthException e) {
            errorResponseHelper.send(response, RestExceptionEnum.FIREBASE_AUTH_EXCEPTION);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
