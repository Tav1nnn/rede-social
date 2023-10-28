package br.com.otavio.clonetwitter.services.exceptions;

import javax.naming.AuthenticationException;

public class InvalidJwtAuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException(String ex) {
        // TODO Auto-generated constructor stub

        super(ex);
    }
}
