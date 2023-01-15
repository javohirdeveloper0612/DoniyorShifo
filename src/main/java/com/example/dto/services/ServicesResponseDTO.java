package com.example.dto.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicesResponseDTO {

    private Integer id;

    private String nameUz;

    private String nameRu;
}
