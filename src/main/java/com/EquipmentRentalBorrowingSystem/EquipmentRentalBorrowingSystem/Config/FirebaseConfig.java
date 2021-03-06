package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    InputStream in =
            getClass().getResourceAsStream("/firebase_config.json");
    @Bean
    public void firebaseInit() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(in)).build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}
