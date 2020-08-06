package com.where2beer.ws.common.security.provider;

import com.where2beer.ws.common.security.model.FirebaseAuthenticationToken;
import com.where2beer.ws.common.security.service.ContextService;
import com.where2beer.ws.user.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

    private final ContextService contextService;

    @Override
    public boolean supports(Class<?> authentication) {
        return FirebaseAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        var token = (FirebaseAuthenticationToken) authentication;
        var claims = token.getPrincipal().getClaims();
        var role = (String) claims.get("role");

        return new FirebaseAuthenticationToken(token.getCredentials(), token.getPrincipal(), generateAuthorities(role));
    }

    private Collection<? extends GrantedAuthority> generateAuthorities(String roleName) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> roles = Arrays.asList(UserRole.values());

        for (int i = 0; i <= roles.indexOf(UserRole.valueOf(roleName)); i++) {
            authorities.add(new SimpleGrantedAuthority(roles.get(i).name()));
        }

        return authorities;
    }
}
