package com.example.dto.auth;

import com.example.enums.AdminRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private Integer id;

    private String username;
    private AdminRole role;

    public JwtDTO(String username, AdminRole role) {
        this.username = username;
        this.role = role;
    }
}
