package com.example.dto;

import com.example.entity.ServicesEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ButtonDTO {

    private Integer id;
    private String buttonNameUz;
    private String buttonNameRu;
    private String buttonDescriptionUz;

    private String buttonDescriptionRu;
    private Integer services;

    private LocalDateTime createdDate;
}
