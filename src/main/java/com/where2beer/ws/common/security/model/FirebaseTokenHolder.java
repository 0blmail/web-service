package com.where2beer.ws.common.security.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class FirebaseTokenHolder {

    private String uid;
    private String email;
    private String name;
    private Map<String, Object> claims;
}
