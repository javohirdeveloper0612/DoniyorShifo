package com.example.dto.doctor;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter

public class DoctorUpdateDTO {

    @NotBlank(message = "firstName_uz cannot be null")
    private String firstName_uz;

    @NotBlank(message = "firstName_ru cannot be null")
    private String firstName_ru;

    @NotBlank(message = "lastName_uz cannot be null")
    private String lastName_uz;

    @NotBlank(message = "lastName_ru cannot be null")
    private String lastName_ru;

    @NotBlank(message = "speciality_uz cannot be null")
    private String speciality_uz;

    @NotBlank(message = "speciality_ru cannot be null")
    private String speciality_ru;

    @NotBlank(message = "phoen cannot be null")
    private String phone;

    @NotBlank(message = "experience cannot be null")
    private Integer experience;

    @NotBlank(message = "description_uz cannot be null")
    private String description_uz;

    @NotBlank(message = "description_ru cannot be null")
    private String description_ru;
}
