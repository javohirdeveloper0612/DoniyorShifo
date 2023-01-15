package com.example.dto.services_button;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ButtonUpdateDTO {
    @NotBlank(message = "buttonNameUz cannot be empty")

    private String buttonNameUz;
    @NotBlank(message = "buttonNameRu cannot be empty")

    private String buttonNameRu;
    @NotBlank(message = "buttonDescriptionUz cannot be empty")

    private String buttonDescriptionUz;
    @NotBlank(message = "buttonDescriptionRu cannot be empty")


    private String buttonDescriptionRu;

}
