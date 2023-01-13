package com.example.controller;

import com.example.dto.services_data.ServicesCreateDTO;
import com.example.dto.services_data.ServicesDataResponseDTO;
import com.example.enums.Language;
import com.example.service.ServicesDataService;
import io.swagger.v3.oas.annotations.Operation;
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
     * @param dto
     * @param language
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create", description = "this method for create new services data")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ServicesCreateDTO dto,
                                    @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.create(dto, language);

        return ResponseEntity.ok(result);
    }

    /**
     * @param id
     * @param language
     * @return
     */
    @Operation(summary = "Get Service Data", description = "this method for get Services Data By Id")
    @GetMapping("/public/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDataResponseDTO result = service.getById(id, language);

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Services Data", description = "This method for delete Services Data by Id (only ADMIN)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        String result = service.deleteById(id, language);
        return ResponseEntity.ok(result);
    }
}
