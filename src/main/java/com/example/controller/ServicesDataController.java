package com.example.controller;

import com.example.dto.services_data.DataUpdateDTO;
import com.example.dto.services_data.ServicesCreateDataDTO;
import com.example.dto.services_data.ServicesDataResponseDTO;
import com.example.enums.Language;
import com.example.service.ServicesDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/services_data")
@Tag(name = "ServicesData Controller", description = "this controller for services Data controller")
public class ServicesDataController {
    private final ServicesDataService service;

    public ServicesDataController(ServicesDataService service) {
        this.service = service;
    }

    /**
     * This method for create new ServicesData only ADMIN
     *
     * @param dto CreateDto
     * @return ServicesDataResponseDTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create", description = "this method for create new services data")
    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@ModelAttribute ServicesCreateDataDTO dto,
                                    @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.create(dto, language);

        return ResponseEntity.ok(result);
    }

    /**
     * This method for getById ServicesData
     *
     * @param id id for ServicesData id
     * @return ServicesDataResponseDTO
     */
    @Operation(summary = "Get Service Data", description = "this method for get Services Data By Id")
    @GetMapping("/public/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.getById(id,language);

//        return ResponseEntity.ok().body(result);
      return   new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * This method for delete ServicesData by ID only Delete ADMIN
     *
     * @param id ServicesData id
     * @return String Deleted successfully
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete Services Data", description = "This method for delete Services Data by Id (only ADMIN)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        String result = service.deleteById(id, language);
        return ResponseEntity.ok(result);
    }

    /**
     * This method for update ServicesData By id only ADMIN
     *
     * @param id  id old servicesData
     * @param dto DataUpdateDTO
     * @return ServicesDataResponseDTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update Services Data", description = "This method for services Data by Id (only ADMIN) ")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody DataUpdateDTO dto,
                                    @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.updateById(id, dto, language);

        return ResponseEntity.ok(result);
    }


}
