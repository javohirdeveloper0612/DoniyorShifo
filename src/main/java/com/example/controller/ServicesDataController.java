package com.example.controller;

import com.example.dto.services_data.DataUpdateDTO;
import com.example.dto.services_data.ServicesCreateDataDTO;
import com.example.dto.services_data.ServicesDataResponseDTO;
import com.example.enums.Language;
import com.example.service.ServicesDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
     * @param dto      CreateDto
     * @param language UZ ,RU
     * @return ServicesDataResponseDTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create", description = "this method for create new services data")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ServicesCreateDataDTO dto,
                                    @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.create(dto, language);

        return ResponseEntity.ok(result);
    }

    /**
     * This method for getById ServicesData
     *
     * @param id       id for ServicesData id
     * @param language UZ,RU
     * @return ServicesDataResponseDTO
     */
    @Operation(summary = "Get Service Data", description = "this method for get Services Data By Id")
    @GetMapping("/public/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.getById(id, language);

        return ResponseEntity.ok(result);
    }

    /**
     * This method for delete ServicesData by ID only Delete ADMIN
     *
     * @param id       ServicesData id
     * @param language UZ,RU
     * @return String Deleted successfully
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete Services Data", description = "This method for delete Services Data by Id (only ADMIN)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        String result = service.deleteById(id, language);
        return ResponseEntity.ok(result);
    }

    /**
     * This method for update ServicesData By id only ADMIN
     *
     * @param id       id old servicesData
     * @param dto      DataUpdateDTO
     * @param language UZ,RU
     * @return ServicesDataResponseDTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update Services Data", description = "This method for services Data by Id (only ADMIN) ")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody DataUpdateDTO dto,
                                    @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.updateById(id, dto, language);

        return ResponseEntity.ok(result);
    }


}
