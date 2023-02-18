package com.example.dto.services_data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter

public class ServicesCreateDataDTO {

    @NotBlank(message = "title_uz cannot be empty")
    private String titleUz;
    @NotBlank(message = "title_ru cannot be empty")
    private String titleRu;
    @NotBlank(message = "description_uz cannot be empty")
    private String descriptionUz;
    @NotBlank(message = "description_ru cannot be empty")
    private String descriptionRu;
    @NotBlank(message = "ButtonId cannot be empty")
    private Integer buttonId;
    @NotNull(message = "MultipartFile cannot be null")
    private MultipartFile file;


}
