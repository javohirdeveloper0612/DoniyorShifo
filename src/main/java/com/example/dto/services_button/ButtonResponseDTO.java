package com.example.dto.services_button;

import com.example.entity.ServicesEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ButtonResponseDTO {

    private Integer id;
    private String buttonNameUz;
    private String buttonNameRu;
    private String titleUz;
    private String titleRu;
    private String descriptionUz;
    private String descriptionRu;
    private String photoUrl;
    private Integer servicesId;

    private ServicesEntity services;
}
