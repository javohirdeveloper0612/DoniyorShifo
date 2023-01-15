package com.example.controller;

import com.example.dto.auth.LoginDTO;
import com.example.dto.auth.LoginResponseDTO;
import com.example.enums.Language;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "AuthController")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    /**
     * This method is used for login to system if it is not exist throw AdminNotFoundException
     *
     * @param dto      LoginDto
     * @param language Language
     * @return LoginResponseDTO
     */
    @Operation(summary = "Login Method", description = "this method for registration")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto, @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {

        log.info(" Login :  username {} ", dto.getUsername());
        LoginResponseDTO response = service.login(dto, language);
        return ResponseEntity.ok().body(response);
    }
}


