package br.com.otavio.clonetwitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PasswordConfig {

    public static final String PBKDF2 = "pbkdf2";

    @Bean
    PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(
                "",
                8,
                185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
        );
        encoders.put(PBKDF2, pbkdf2PasswordEncoder);

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(PBKDF2, encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);
        return passwordEncoder;
    }
}
