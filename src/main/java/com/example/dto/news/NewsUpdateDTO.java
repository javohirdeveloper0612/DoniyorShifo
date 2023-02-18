package com.example.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter

public class NewsUpdateDTO {
    @NotBlank(message = "title_uz cannot be null or empty")
    private String title_uz;

    @NotBlank(message = "title_ru cannot be null or empty")
    private String title_ru;

    @NotBlank(message = "description_uz cannot be null or empty")
    private String description_uz;

    @NotBlank(message = "description_ru cannot be null or empty")
    private String description_ru;

    @NotNull(message = "file cannot be null")
    private MultipartFile file;
}
