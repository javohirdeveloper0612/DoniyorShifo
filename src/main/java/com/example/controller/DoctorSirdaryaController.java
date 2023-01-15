package com.example.controller;
import com.example.dto.DoctorDTO;
import com.example.enums.Language;
import com.example.service.DoctorSirdaryaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/doctorSirdarya")
@Tag(name = "Doctor Sirdarya Controller API", description = "Api list for Doctor")
public class DoctorSirdaryaController {

    private final DoctorSirdaryaService doctorSirdaryaService;

    @Autowired
    public DoctorSirdaryaController(DoctorSirdaryaService doctorSirdaryaService) {
        this.doctorSirdaryaService = doctorSirdaryaService;
    }


    /**
     * this method is used to create a doctor from the ADMIN side and
     * returns doctorDTO if the doctor is created.
     *
     * @param photoId  Integer
     * @param dto      DoctorDTO
     * @param language Language
     * @return DoctorDTO
     */

 //   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/{photoId}")
    @Operation(summary = "Create Doctor Sirdarya ADMIN", description = "this method is used by ADMIN to create doctor Sirdarya")
    public ResponseEntity<DoctorDTO> create(
            @PathVariable Integer photoId,
            @RequestBody DoctorDTO dto,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

            return doctorSirdaryaService.create(photoId, dto, language);
    }

    /**
     * this method is used by ADMIN to get information of Sirdaryo
     * doctor by giving the id number of the doctor, if a doctor
     * with the given id number is found DoctorDTO is returned...
     *
     * @param id       Integer
     * @param language Language
     * @return DoctorDTO
     */

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("getDoctor/{id}")
    @Operation(summary = "Get Doctor Sirdarya By id", description = "this method is used by ADMIN get Doctor Sirdarya By id")
    public ResponseEntity<DoctorDTO> getDoctorById(
            @PathVariable Integer id,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

            return doctorSirdaryaService.getDoctorById(id,language);
    }





}
