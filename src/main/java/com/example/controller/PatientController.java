package com.example.controller;

import com.example.dto.patient.CreatePatientDto;
import com.example.enums.Language;
import com.example.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/public/create_patient")
    @Operation(summary = "Creating Patient method", description = "This method is used for save Patient data IN DataBase")
    public ResponseEntity<?> creationPatient(@Valid @RequestBody CreatePatientDto patientDto) {
        return patientService.createPatient(patientDto, Language.UZ);
    }


    /**
     * This method is used for getting patient data by id If it is not exist throw PatientNotFoundException
     *
     * @param id Integer
     * @return ResponsePatientDTO
     */


    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("view_patient/{id}")
    @Operation(summary = "Getting patient By id method", description = "This method is used for getting patient data by id")
    public ResponseEntity<?> getPatientById(@PathVariable Integer id) {
        return patientService.getPatientById(id, Language.UZ);

    }


    /**
     * This method is used for getting all the patient data order by createdDate
     *
     * @return List<PatientEntity></PatientEntity>
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/view_patient")
    @Operation(summary = "Viewing All the Patient data method", description = "This method is used for getting all the Patient data order by createdDate")
    public ResponseEntity<?> getAllPatientData() {
        return patientService.getAll();
    }

}
