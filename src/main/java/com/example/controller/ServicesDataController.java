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
     * @param dto CreateDto
     * @return ServicesDataResponseDTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create", description = "this method for create new services data")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ServicesCreateDataDTO dto) {
        ServicesDataResponseDTO result = service.create(dto, Language.UZ);

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
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        ServicesDataResponseDTO result = service.getById(id, Language.UZ);

        return ResponseEntity.ok(result);
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
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        String result = service.deleteById(id, Language.UZ);
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
                                    @RequestBody DataUpdateDTO dto) {
        ServicesDataResponseDTO result = service.updateById(id, dto, Language.UZ);

        return ResponseEntity.ok(result);
    }


}
