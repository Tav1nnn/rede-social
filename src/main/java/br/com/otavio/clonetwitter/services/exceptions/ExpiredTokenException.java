package br.com.otavio.clonetwitter.services.exceptions;

public class ExpiredTokenException extends RuntimeException{
    public ExpiredTokenException(String msg) {
        super(msg);
    }
}
