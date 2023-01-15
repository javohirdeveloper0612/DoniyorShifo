package com.example.exp.doctor;

public class DoctorNotFoundListException extends RuntimeException{

    public DoctorNotFoundListException(String message) {
        super(message);
    }
}
