package com.where2beer.ws.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {

    private final FirebaseProperties properties;

    @PostConstruct
    public void init() throws IOException {
        InputStream stream = FirebaseConfig.class.getResourceAsStream(properties.getCredentialPath());
        var options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(stream))
                .setDatabaseUrl(properties.getDatabaseUrl())
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Bean
    public DatabaseReference database() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
