package com.example.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedNewsDto {

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
