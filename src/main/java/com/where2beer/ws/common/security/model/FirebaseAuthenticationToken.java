package com.where2beer.ws.common.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {

    private final String uid;
    private final FirebaseTokenHolder holder;

    public FirebaseAuthenticationToken(String uid, FirebaseTokenHolder holder) {
        super(null);
        this.uid = uid;
        this.holder = holder;
        super.setAuthenticated(false);
    }

    public FirebaseAuthenticationToken(String uid, FirebaseTokenHolder holder, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.uid = uid;
        this.holder = holder;
        super.setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return this.uid;
    }

    @Override
    public FirebaseTokenHolder getPrincipal() {
        return this.holder;
    }
}
