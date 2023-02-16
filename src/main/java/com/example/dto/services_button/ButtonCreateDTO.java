package com.example.dto.services_button;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ButtonCreateDTO {

    @NotBlank(message = "ButtonNameUz cannot be empty")
    private String buttonNameUz;
    @NotBlank(message = "buttonNameRu cannot be empty")
    private String buttonNameRu;
    @NotBlank(message = "buttonDescriptionUz cannot be empty")
    private String buttonDescriptionUz;
    @NotBlank(message = "buttonDescriptionRu cannot be empty")

    private String buttonDescriptionRu;
    @NotNull(message = "servicesId cannot be empty")
    private Integer servicesId;


}
