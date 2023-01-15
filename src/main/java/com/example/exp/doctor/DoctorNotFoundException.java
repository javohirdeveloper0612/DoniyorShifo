package com.example.exp.doctor;

public class DoctorNotFoundException extends RuntimeException{

    public DoctorNotFoundException(String message) {
        super(message);
    }

}
