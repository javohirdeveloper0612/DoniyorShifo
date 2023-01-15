package com.example.dto.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResumeDto {
    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String description;
    private Integer resumeFileId;
}
