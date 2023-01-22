package com.example.controller;

import com.example.dto.services.ServicesResponseDTO;
import com.example.dto.services.ServicesCreateDTO;
import com.example.enums.Language;
import com.example.service.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/services")
@Tag(name = "Services Controller")
public class ServicesController {

    private final ServicesService service;

    /**
     * This method for Create new Services
     *
     * @param dto      ServicesCreateDto
     * @param language UZ,RU default language UZ
     * @return ServicesResponseDTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create services method", description = "Method for create services (ONLY ADMIN) ")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ServicesCreateDTO dto,
                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesResponseDTO result = service.create(dto, language);

        return ResponseEntity.ok().body(result);
    }

    public ServicesController(ServicesService service) {
        this.service = service;
    }

    /**
     * This method for get Services by id
     *
     * @param id       enter the service id you want to search for
     * @param language UZ,RU default language uz
     * @return ServicesResponseDTO
     */
    @Operation(summary = "Get Services", description = "this method get Services by Id ")
    @GetMapping("/public/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesResponseDTO result = service.getById(id, language);

        return ResponseEntity.ok(result);
    }

    /**
     * This method for Services List
     *
     * @param language UZ,RU default UZ
     * @return ServicesResponseDTO
     */
    @Operation(summary = "Services List", description = "this method get Services List")
    @GetMapping("/public/get_list")
    public ResponseEntity<?> getList() {
        List<ServicesResponseDTO> dtoList = service.getList();

        return ResponseEntity.ok().body(dtoList);
    }

    /**
     * This method for Services delete by id
     * @param id Enter the id of the services you want to launch
     * @param language UZ,RU
     * @return String
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete Services", description = "this method for delete services By Id (only ADMIN)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        String result = service.deleteById(id, language);
        return ResponseEntity.ok(result);
    }

    /**
     * This method for Update Services by id
     *
     * @param id       enter the Services id you want to change
     * @param dto      ServicesCreateDTO
     * @param language UZ,RU
     * @return ButtonResponseDto, Throws a ServicesNotFoundException if the id is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update Services", description = "This method for update Services by Id (only ADMIN)")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody ServicesCreateDTO dto,
                                        @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesResponseDTO result = service.updateById(id, dto, language);

        return ResponseEntity.ok(result);
    }


}
