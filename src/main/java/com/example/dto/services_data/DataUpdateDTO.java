package com.example.dto.services_data;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class DataUpdateDTO {
    @NotBlank(message = "title_uz cannot be empty")
    private String titleUz;
    @NotBlank(message = "title_ru cannot be empty")
    private String titleRu;
    @NotBlank(message = "description_uz cannot be empty")
    private String descriptionUz;
    @NotBlank(message = "description_ru cannot be empty")
    private String descriptionRu;

}
