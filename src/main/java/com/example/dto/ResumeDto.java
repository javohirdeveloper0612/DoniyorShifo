package com.example.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {

    private Integer id;
    @NotBlank(message = "fullName cannot be empty")
    String fullName;


    @NotBlank(message = "phone cannot be null")
    @Length(max = 13, min = 13)
    private String phone;


    @NotBlank(message = "email cannot be null")
    @Email
    private String email;


    private String description;
}
