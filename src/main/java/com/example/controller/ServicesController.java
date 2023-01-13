package com.example.controller;

import com.example.dto.ServicesDTO;
import com.example.enums.Language;
import com.example.service.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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

    public ServicesController(ServicesService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create services method", description = "Method for create services (ONLY ADMIN) ")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ServicesDTO dto,
                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDTO result = service.create(dto, language);

        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Get Services", description = "this method get Services by Id ")
    @GetMapping("/public/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        ServicesDTO result = service.getById(id, language);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Services List", description = "this method get Services List")
    @GetMapping("/public/get_list")
    public ResponseEntity<?> getList(@RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        List<ServicesDTO> dtoList  = service.getList(language);

        return ResponseEntity.ok().body(dtoList);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Services",description = "this method for delete services By Id (only ADMIN)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id")Integer id,
                                    @RequestParam(name = "Accept-Language",defaultValue = "UZ")Language language){
        String result = service.deleteById(id,language);
        return ResponseEntity.ok(result);
    }


}
