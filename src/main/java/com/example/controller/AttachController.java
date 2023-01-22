package com.example.controller;

import com.example.enums.Language;
import com.example.service.AttachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "Attach Controller", description = "This controller for file uploading and file downloading")
@RestController
@RequestMapping("/api/attach")
public class AttachController {

    private final AttachService attachService;

    @Autowired
    public AttachController(AttachService attachService) {
        this.attachService = attachService;
    }


    /**
     * This method is used for file uploading in DataBase
     * If File Name is Empty  ,throw FileNameNotFoundException()
     *
     * @param file  MultipartHttpServletRequest
     * @return AttachDTO
     */
    @PostMapping(value = "/public/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload method", description = "This method uploads the file in DataBase")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        return attachService.uploadFile(file);
    }


    /**
     * This method is used for downloading file
     * If file is not exist DB, throw FileNotFoundException
     *
     * @param id       Integer
     * @param response HttpServletResponse
     * @return Message
     */


    @GetMapping("/public/download/{id}")
    @Operation(summary = "Download method", description = "This method used for downloading file")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id, HttpServletResponse response, Language language) {
        return attachService.downloadFile(id, response, language);
    }

}
