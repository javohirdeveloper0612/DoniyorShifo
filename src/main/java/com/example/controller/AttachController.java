package com.example.controller;

import com.example.service.AttachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Tag(name = "Attach Controller", description = "This controller for file uploading and file downloading")
@RestController
@RequestMapping("/api/attach")
public class AttachController {

    private final AttachService attachService;

    @Autowired
    public AttachController(AttachService attachService) {
        this.attachService = attachService;
    }


    @PostMapping("/upload")
    @Operation(summary = "Upload method", description = "This method uploads the file in DataBase")
    public ResponseEntity<?> uploadFile(MultipartHttpServletRequest request) {
        return attachService.uploadFile(request);
    }


}
