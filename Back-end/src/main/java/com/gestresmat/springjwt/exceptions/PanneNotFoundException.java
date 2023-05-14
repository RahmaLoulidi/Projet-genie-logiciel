package com.gestresmat.springjwt.exceptions;

public class PanneNotFoundException extends  RuntimeException{
    public PanneNotFoundException(String message) {
        super(message);
    }
}