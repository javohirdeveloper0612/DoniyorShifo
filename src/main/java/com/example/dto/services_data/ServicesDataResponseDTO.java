package com.example.dto.services_data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ServicesDataResponseDTO {

    private Integer id;

    private String titleUz;
    private String titleRu;
    private String descriptionUz;
    private String descriptionRu;

    private Integer attachId;

    private Integer buttonId;

    private LocalDateTime createdDate;
}
