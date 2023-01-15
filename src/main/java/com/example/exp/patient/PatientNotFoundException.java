package com.example.exp.patient;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
