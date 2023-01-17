package com.example.exp.servicesButton;

public class ButtonNotExistsException extends RuntimeException {
    public ButtonNotExistsException(String message) {
        super(message);
    }
}
