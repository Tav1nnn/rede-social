package br.com.otavio.clonetwitter.config;

import br.com.otavio.clonetwitter.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtService service;

    public SecurityConfig(JwtService service) {
        this.service = service;
    }

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(
                                        "/api/user/v1/cadastrar",
                                        "/api/user/v1/login",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                        ).permitAll()
                                .requestMatchers(
                                        "/api/user/v1/findbyid/**",
                                        "api/user/v1/publicacao",
                                        "api/user/v1/curtida",
                                        "api/user/v1/comentario"
                                        ).authenticated()
                )
                .cors()
                .and()
                .apply(new JwtConfig(service))
                .and()
                .build();
    }
}
