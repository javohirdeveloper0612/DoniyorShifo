package com.example.dto.services_button;

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
public class ButtonCreateDTO {

    @NotBlank(message = "ButtonNameUz cannot be empty")
    private String buttonNameUz;
    @NotBlank(message = "buttonNameRu cannot be empty")
    private String buttonNameRu;
    @NotBlank(message = "title_uz cannot be empty")
    private String titleUz;
    @NotBlank(message = "title_ru cannot be empty")
    private String titleRu;

    @NotBlank(message = "buttonDescriptionUz cannot be empty")
    private String descriptionUz;
    @NotBlank(message = "buttonDescriptionRu cannot be empty")
    private String descriptionRu;

    @NotNull(message = "MultipartFile cannot be null")
    private MultipartFile file;

    @NotNull(message = "servicesId cannot be empty")
    private Integer servicesId;


}
