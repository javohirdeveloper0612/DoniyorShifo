package com.example.controller;
import com.example.dto.doctor.DoctorCreationDTO;
import com.example.dto.doctor.DoctorResponseDTO;
import com.example.dto.doctor.DoctorUpdateDTO;
import com.example.enums.Language;
import com.example.service.DoctorTashkentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctortashkent")
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
     * @param dto      DoctorDTO
     * @param language Language
     * @return doctorTashkentService.create(photoId, dto, language)
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/create")
    @Operation(summary = "Create Doctor Tashkent ADMIN", description = "this method is used by ADMIN to create doctor Tashkent")
    public ResponseEntity<?> create(
            @RequestBody DoctorCreationDTO dto,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

        DoctorResponseDTO result = doctorTashkentService.create(dto, language);
        return ResponseEntity.ok().body(result);

    }

    /**
     * this method is used by ADMIN to get information of Tashkent
     * doctor by giving the id number of the doctor, if a doctor
     * with the given id number is found DoctorDTO is returned...
     *
     * @param id       Integer
     * @param language Language
     * @return doctorTashkentService.getDoctorById(id, language)
     */

    @GetMapping("/public/get/{id}")
    @Operation(summary = "Get Doctor Tashkent By id", description = "this method is used by ADMIN get Doctor Tashkent By id")
    public ResponseEntity<?> getDoctorById(
            @PathVariable Integer id,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

        DoctorResponseDTO result = doctorTashkentService.getDoctorById(id, language);
        return ResponseEntity.ok(result);

    }

    /**
     * this method is used by ADMIN to update Tashkent Doctor
     * by id number returns true if the data is updated
     *
     * @param id       Integer
     * @param language Language
     * @return doctorTashkentService.update(id, doctorDTO, language)
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update/{id}")
    @Operation(summary = "Update Doctor Tashkent By id", description = "this method is used by ADMIN to update Tashkent doctor by id number")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody DoctorUpdateDTO doctorDTO,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

        DoctorResponseDTO result = doctorTashkentService.update(id, doctorDTO, language);
        return ResponseEntity.ok().body(result);

    }

    /**
     * this method is used by ADMIN to delete Tashkent doctor
     * by id number return true if doctor is deleted
     *
     * @param id       Integer
     * @param language Language
     * @return result
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Doctor Tashkent By id", description = "this method is used by ADMIN to delete Tashkent doctor by id number")
    public ResponseEntity<?> delete(
            @PathVariable Integer id,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

        String result = doctorTashkentService.delete(id, language);
        return ResponseEntity.ok().body(result);

    }

    /**
     * this method is used to get the list of Sirdarya
     * doctors using pagination
     *
     * @param page     int
     * @param size     int
     * @param language Language
     * @return ResponseEntity.ok(allNews)
     */


    @Operation(summary = "Doktor Tashkent get by pagination", description = "this method get Doctor Tashkent Pagination")
    @GetMapping("/public/getdoctorlistpagination")
    public ResponseEntity<?> getNewsPages(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1") int size,
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {


        Page<DoctorResponseDTO> allNews = doctorTashkentService.getDoctorPage(page, size, language);
        return ResponseEntity.ok(allNews);

    }

    @Operation(summary = "Doctor list", description = "This method for Doctor list (only ADMIN)")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/get_list")
    public ResponseEntity<?> getAllList(@RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {

        List<DoctorResponseDTO> result = doctorTashkentService.getAllList(language);

        return ResponseEntity.ok().body(result);
    }


}
