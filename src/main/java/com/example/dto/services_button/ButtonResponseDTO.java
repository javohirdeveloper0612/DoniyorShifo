package com.example.dto.services_button;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ButtonResponseDTO {


    private Integer id;
    private String buttonNameUz;
    private String buttonNameRu;
    private String buttonDescriptionUz;

    private String buttonDescriptionRu;
    private Integer servicesId;

    private LocalDateTime createdDate;
}
