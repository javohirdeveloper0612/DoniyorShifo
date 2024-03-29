package com.example.controller;

import com.example.dto.doctor.DoctorCreationDTO;
import com.example.dto.doctor.DoctorResponseDTO;
import com.example.dto.doctor.DoctorUpdateDTO;
import com.example.enums.Language;
import com.example.service.DoctorSirdaryaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/doctorsirdarya")
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
     * @param dto      DoctorDTO
     * @return result
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create Doctor Sirdarya ADMIN", description = "this method is used by ADMIN to create doctor Sirdarya")
    public ResponseEntity<?> create(
            @Valid
            @ModelAttribute DoctorCreationDTO dto,
            @RequestHeader(name = "Accept-Language",defaultValue = "UZ") Language language) {

        log.info("created S Doctors phone {} ", dto.getPhone());
        DoctorResponseDTO result = doctorSirdaryaService.create(dto, language);
        return ResponseEntity.ok().body(result);

    }

    /**
     * this method is used by ADMIN to get information of Sirdaryo
     * doctor by giving the id number of the doctor, if a doctor
     * with the given id number is found DoctorDTO is returned...
     *
     * @param id       Integer
     * @return result
     */

    @GetMapping("/public/get/{id}")
    @Operation(summary = "Get Doctor Sirdarya By id", description = "this method is used by ADMIN get Doctor Sirdarya By id number")
    public ResponseEntity<?> getDoctorById(
            @PathVariable("id") Integer id,
            @RequestHeader(name = "Accept-Language",defaultValue = "UZ") Language language) {

        log.info("get  S Doctor by id id{} " , id);
        DoctorResponseDTO result = doctorSirdaryaService.getDoctorById(id, language);
        return ResponseEntity.ok().body(result);

    }

    /**
     * this method is used by ADMIN to update Sirdarya Doctor
     * by id number returns true if the data is updated
     *
     * @param id       Integer
     * @return result
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("update/{id}")
    @Operation(summary = "Update Doctor Sirdarya By id", description = "this method is used by ADMIN to update Sirdaryo doctor by id number")
    public ResponseEntity<?> update(
            @Valid
            @PathVariable Integer id,
            @RequestBody DoctorUpdateDTO doctorDTO,
            @RequestHeader(name = "Accept-Language",defaultValue = "UZ") Language language) {

        log.info(" update s Doctor id{} , doctorPhone{} , doctorUsername{}" , id, doctorDTO.getPhone() ,doctorDTO.getFirstName_ru() );
        DoctorResponseDTO result = doctorSirdaryaService.update(id, doctorDTO,language);
        return ResponseEntity.ok().body(result);

    }

    /**
     * this method is used by ADMIN to delete Sirdarya doctor
     * by id number return true if doctor is deleted
     *
     * @param id       Integer
     * @return result
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Doctor Sirdarya By id", description = "this method is used by ADMIN to delete Sirdarya doctor by id number")
    public ResponseEntity<?> delete(
            @PathVariable Integer id,
            @RequestHeader(name = "Accept-Language",defaultValue = "UZ") Language language) {

        log.info(" delete s Doctor by id :  id{} " , id);
        String result = doctorSirdaryaService.delete(id, language);
        return ResponseEntity.ok().body(result);

    }

    /**
     * this method is used to get the list of Sirdarya
     * doctors using pagination
     *
     * @param page     int
     * @param size     int
     * @return ResponseEntity.ok(allNews)
     */

    @Operation(summary = "Doctor Sirdarya get by pagination", description = "this method get Doctor Sirdarya Pagination")
    @GetMapping("/public/getdoctorlistpagination")
    public ResponseEntity<?> getNewsPages(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1") int size,
            @RequestHeader(name = "Accept-Language",defaultValue = "UZ") Language language) {


        Page<DoctorResponseDTO> allNews = doctorSirdaryaService.getDoctorPage(page, size, language);
        return ResponseEntity.ok().body(allNews);

    }


    @Operation(summary = "Doctor list", description = "This method for Doctor list (only ADMIN)")
    @GetMapping("/public/get_list")
    public ResponseEntity<?> getAllList(@RequestHeader(name = "Accept-Language",defaultValue = "UZ")Language language) {

        List<DoctorResponseDTO> result = doctorSirdaryaService.getAllList(language);

        return ResponseEntity.ok().body(result);
    }


}
