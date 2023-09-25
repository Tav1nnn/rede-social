package br.com.otavio.clonetwitter.services.exceptions;

public class UserAlreadyShareException extends RuntimeException{

    public UserAlreadyShareException(String msg) {
        super(msg);
    }
}
