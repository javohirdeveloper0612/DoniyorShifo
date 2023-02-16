package com.example.exp.doctor;

public class DoctorPhoneAlreadyExists extends RuntimeException{

    public DoctorPhoneAlreadyExists(String message) {
        super(message);
    }
}

