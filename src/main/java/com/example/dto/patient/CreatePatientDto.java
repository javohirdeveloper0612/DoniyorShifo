package com.example.dto.patient;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientDto {

    @NotBlank(message = "firstName cannot be null or empty")
    private String firstName;

    @NotBlank(message = "lastName cannot be null or empty")
    private String lastName;

    @NotBlank(message = "phone cannnot be null or empty")
    @Size(max = 13, min = 13)
    private String phone;

    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "doctorId cannot be null")
    private Integer doctorId;


}
