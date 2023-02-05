package com.example.dto.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ServicesCreateDTO {
    @NotBlank
    private String nameUz;
    @NotBlank
    private String nameRu;
}
