package com.gestresmat.springjwt.exceptions;

public class RessourceIntrouvableException extends RuntimeException {

    public RessourceIntrouvableException(String message) {
        super(message);
    }

    public RessourceIntrouvableException(String message, Throwable cause) {
        super(message, cause);
    }
}