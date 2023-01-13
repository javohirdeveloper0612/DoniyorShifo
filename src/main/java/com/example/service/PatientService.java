package com.example.service;

import com.example.dto.PatientDto;
import com.example.enums.Language;
import com.example.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;


    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public ResponseEntity<?> createPatient(PatientDto patientDto, Language language) {
        return null;
    }

}
