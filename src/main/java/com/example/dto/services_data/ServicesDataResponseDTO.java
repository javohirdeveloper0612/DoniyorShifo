package com.example.dto.services_data;

import com.example.dto.attach.AttachDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
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

    private String photoUrl;

    private Integer buttonId;

    private LocalDateTime createdDate;
}
