package com.example.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePatientDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private Integer doctorId;
}
