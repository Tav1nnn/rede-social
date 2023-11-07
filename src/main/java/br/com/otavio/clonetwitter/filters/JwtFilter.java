package br.com.otavio.clonetwitter.filters;

import br.com.otavio.clonetwitter.config.SecurityConfig;
import br.com.otavio.clonetwitter.services.JwtService;
import br.com.otavio.clonetwitter.services.exceptions.InvalidJwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean{

    @Autowired
    private JwtService service;

    public JwtFilter(JwtService service) {
        this.service = service;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = service.resolveToken((HttpServletRequest) servletRequest);


            if (token != null && service.validateToken(token)) {
                Authentication auth = service.getAuthentication(token);
                if(auth != null){
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
