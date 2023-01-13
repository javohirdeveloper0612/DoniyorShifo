package com.example.controller;
import com.example.dto.DoctorDTO;
import com.example.enums.Language;
import com.example.service.DoctorTashkentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctorTashkent")
@Tag(name = "Doctor Tashkent Controller API", description = "Api list for Doctor Tashkent")
public class DoctorTashkentController {


    private final DoctorTashkentService doctorTashkentService;

    @Autowired
    public DoctorTashkentController(DoctorTashkentService doctorTashkentService) {
        this.doctorTashkentService = doctorTashkentService;
    }


    /**
     * this method is used to create doctors coming to clinic from Toshken by ADMIN
     * if docto is created returns DoctorDTO
     *
     * @param photoId  Integer
     * @param dto      DoctorDTO
     * @param language Language
     * @return DoctorDTO
     */

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/{photoId}")
    @Operation(summary = "Create Doctor Tashkent ADMIN", description = "this method is used by ADMIN to create doctor Tashkent")
    public ResponseEntity<DoctorDTO> create(
            @PathVariable Integer photoId,
            @RequestBody DoctorDTO dto,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

            return doctorTashkentService.create(photoId, dto, language);
    }

    /**
     * this method is used by ADMIN to get information of Tashkent
     * doctor by giving the id number of the doctor, if a doctor
     * with the given id number is found DoctorDTO is returned...
     *
     * @param id       Integer
     * @param language Language
     * @return DoctorDTO
     */

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("getDoctor/{id}")
    @Operation(summary = "Get Doctor Tashkent By id", description = "this method is used by ADMIN get Doctor Tashkent By id")
    public ResponseEntity<DoctorDTO> getDoctorById(
            @PathVariable Integer id,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

            return doctorTashkentService.getDoctorById(id,language);
    }












}
