package com.example.dto.doctor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class DoctorCreationDTO {


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

    @Size(min = 13,max =13)
    @NotBlank(message = "phone cannot be null or empty")
    private String phone;

    @NotBlank(message = "experience cannot be null")
    private Integer experience;

    @NotBlank(message = "description_uz cannot be null")
    private String description_uz;

    @NotBlank(message = "description_ru cannot be null")
    private String description_ru;

    private Integer photoId;
}
