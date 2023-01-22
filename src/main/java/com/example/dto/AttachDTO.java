package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachDTO {
    private Integer id;
    private String fileOriginalName;
    private long size;
    private String type;

}
