package com.example.exp.services;

public class ServicesNotFoundException extends RuntimeException{
    public ServicesNotFoundException(String message) {
        super(message);
    }
}
