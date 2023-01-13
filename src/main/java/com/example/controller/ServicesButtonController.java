package com.example.controller;

import com.example.dto.ButtonDTO;
import com.example.enums.Language;
import com.example.service.ServicesButtonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services_button")
@Tag(name = "Sevices Button Controller", description = "this Controller for add button and delete button get list button and get by id")
public class ServicesButtonController {

    private final ServicesButtonService service;

    public ServicesButtonController(ServicesButtonService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create", description = "this method for create new button (only ADMIN)")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ButtonDTO dto,
                                    @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        ButtonDTO result = service.create(dto, language);
        return ResponseEntity.ok().body(result);
    }

    /**
     *
     * @param id
     * @param language
     * @return String
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete button", description = "This method for delete Services Button by Id (only ADMIN)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    @RequestParam(name = "Accept-Language", defaultValue = "UZ") Language language) {
        String result = service.deleteById(id, language);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get Button",description = "This method for get Button by Id ")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     @RequestParam(name = "Accept-Language",defaultValue = "UZ")Language language){
        return null;
    }
}
