package com.example.service;

import com.example.dto.auth.LoginDTO;
import com.example.dto.auth.LoginResponseDTO;
import com.example.entity.AdminEntity;
import com.example.enums.Language;
import com.example.exp.AdminNotFoundException;
import com.example.repository.AdminRepository;
import com.example.util.JwtUtil;
import com.example.util.MD5;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final AdminRepository repository;

    private final ResourceBundleService resourceBundleService;

    public AuthService(AdminRepository repository, ResourceBundleService resourceBundleService) {
        this.repository = repository;
        this.resourceBundleService = resourceBundleService;
    }


    /**
     * This method is used for login to system if it is not exist throw AdminNotFoundException
     *
     * @param dto      LoginDto
     * @param language Language
     * @return LoginResponseDTO
     */
    public LoginResponseDTO login(LoginDTO dto, Language language) {

        Optional<AdminEntity> optional = repository.findByUsernameAndPassword(dto.getUsername(), MD5.md5(dto.getPassword()));
        if (optional.isEmpty()) {
            throw new AdminNotFoundException(resourceBundleService.getMessage("admin.not.found", language.name()));
        }

        AdminEntity entity = optional.get();

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setUsername(entity.getUsername());
        responseDTO.setRole(entity.getRole());
        responseDTO.setToken(JwtUtil.encode(entity.getUsername(), entity.getRole()));

        return responseDTO;
    }
}
