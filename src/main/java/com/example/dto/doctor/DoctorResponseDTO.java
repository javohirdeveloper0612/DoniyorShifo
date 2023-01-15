package com.example.dto.doctor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DoctorResponseDTO {

    private Integer id;
    private String firstName_uz;
    private String firstName_ru;
    private String lastName_uz;
    private String lastName_ru;
    private String speciality_uz;
    private String speciality_ru;
    private String phone;
    private Integer experience;
    private String description_uz;
    private String description_ru;
    private Integer photoId;
    private String role;


}
