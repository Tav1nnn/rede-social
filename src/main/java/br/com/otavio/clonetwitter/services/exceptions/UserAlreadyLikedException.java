package br.com.otavio.clonetwitter.services.exceptions;

public class UserAlreadyLikedException extends RuntimeException{

    public UserAlreadyLikedException(String msg) {
        super(msg);
    }
}
