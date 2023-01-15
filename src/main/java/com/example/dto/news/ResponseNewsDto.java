package com.example.dto.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseNewsDto {

    private Integer id;

    private String title_uz;

    private String title_ru;


    private String description_uz;

    private String description_ru;

    private Integer photoId;


}
