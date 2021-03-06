package com.where2beer.ws.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.firebase")
public class FirebaseProperties {

    private String databaseUrl;
    private String credentialPath;
}
