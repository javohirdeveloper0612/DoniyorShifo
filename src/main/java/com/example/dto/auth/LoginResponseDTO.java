package com.example.dto.auth;

import com.example.enums.AdminRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponseDTO {
    private String name;
    private String username;
    private AdminRole role;
    private String token;
}
