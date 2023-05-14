package com.gestresmat.springjwt.exceptions;

public class TechnicienNotFoundException extends  RuntimeException{
    public TechnicienNotFoundException(String message) {
        super(message);
    }
}
