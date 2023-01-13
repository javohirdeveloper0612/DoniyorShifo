package com.example.controller;

import com.example.dto.PatientDto;
import com.example.enums.Language;
import com.example.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@Tag(name = "Patient Controller")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * This method is used for saving Patient data in DB If Photo not found throw FileNotFoundException
     *
     * @param patientDto PatientDto
     * @return PatientDto
     */
    @PostMapping("/createPatient")
    @Operation(summary = "Create Patient method", description = "This method is used for save Patient data IN DataBase")
    public ResponseEntity<?> creationPatient(@Valid @RequestBody PatientDto patientDto
            , @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        return patientService.createPatient(patientDto,language);
    }

}
