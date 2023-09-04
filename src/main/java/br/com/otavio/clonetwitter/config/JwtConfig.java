package br.com.otavio.clonetwitter.config;

import br.com.otavio.clonetwitter.filters.JwtFilter;
import br.com.otavio.clonetwitter.services.JwtService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private JwtService service;

    public JwtConfig(JwtService service) {
        this.service = service;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtFilter filter = new JwtFilter(service);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
