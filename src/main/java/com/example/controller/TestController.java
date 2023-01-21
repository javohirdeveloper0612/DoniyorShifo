package com.example.controller;

import com.example.dto.services.ServicesResponseDTO;
import com.example.enums.Language;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/test")
@Tag(name = "test", description = "test controller")
public class TestController {
    @Operation(summary = "test method", description = "This method for test")
    @GetMapping("/public/1")
    public ResponseEntity<?> get(@RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        return ResponseEntity.ok().body("SAlomlar");
    }

    @Operation(summary = "Services List", description = "this method get Services List")
    @GetMapping("/public/get_list/{id}")
    public HttpEntity<String> getList(@PathVariable("id") Integer id) {

        return ResponseEntity.ok().body("Salom: " + id.toString());
    }
}
