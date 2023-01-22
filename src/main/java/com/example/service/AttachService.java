package com.example.service;

import com.example.dto.AttachDTO;
import com.example.entity.AttachContentEntity;
import com.example.entity.AttachEntity;
import com.example.enums.Language;
import com.example.exp.attach.FileNameNotFoundException;
import com.example.exp.attach.FileNotFoundException;
import com.example.repository.AttachmentContentRepository;
import com.example.repository.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachService {
    private final ResourceBundleService resourceBundleService;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;


    @Autowired
    public AttachService(ResourceBundleService resourceBundleService,
                         AttachmentRepository attachmentRepository,
                         AttachmentContentRepository contentRepository) {
        this.resourceBundleService = resourceBundleService;
        this.attachmentRepository = attachmentRepository;
        this.contentRepository = contentRepository;
    }


    /**
     * This method is used for file uploading in DataBase
     * If File Name is Empty  ,throw FileNameNotFoundException()
     *
     * @param file  MultipartHttpServletRequest
     * @return AttachDTO
     */


    public ResponseEntity<?> uploadFile(MultipartFile file) {

        /*Iterator<String> fileNames = request.getFi();
        MultipartFile file = request.getFile(fileNames.next());

        if (file == null) {
            throw new FileNameNotFoundException(
                    resourceBundleService.getMessage("fileName.not.found", language.name()));
        }*/

        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();

        //we set originalFilename, size, contentType to AttachDto to response Frontend


        //we saved AttachEntity object in DB
        AttachEntity attachment = new AttachEntity();
        attachment.setOriginalName(originalFilename);
        attachment.setSize(size);
        attachment.setType(contentType);

        AttachEntity savedAttach = attachmentRepository.save(attachment);
        AttachDTO attachDTO = new AttachDTO(savedAttach.getId(), originalFilename, size, contentType);


        try {
            // we saved AttachContentEntity object in DB
            AttachContentEntity attachmentContent = new AttachContentEntity();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttach(savedAttach);
            contentRepository.save(attachmentContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(attachDTO);
    }


    /**
     * This method is used for downloading file
     * If file is not exist DB, throw FileNotFoundException
     *
     * @param id       Integer
     * @param response HttpServletResponse
     * @return Message
     */


    public ResponseEntity<?> downloadFile(Integer id, HttpServletResponse response, Language language) {

        //we get the AttachEntity object from DB
        Optional<AttachEntity> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty()) {
            throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));
        }

        AttachEntity attachment = optionalAttachment.get();

        //we get the AttachEntity object from DB
        Optional<AttachContentEntity> optional = contentRepository.findByAttachId(id);
        if (optional.isEmpty()) {
            throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));
        }


        AttachContentEntity attachContentEntity = optional.get();
        byte[] content = attachContentEntity.getBytes();

        response.setHeader("Content-Disposition", "attachment; fileName=" +
                attachment.getOriginalName());

        response.setContentType(attachment.getType());
        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setFileOriginalName(attachment.getOriginalName());
        attachDTO.setType(attachment.getType());
        attachDTO.setSize(attachment.getSize());
        attachDTO.setId(attachment.getId());

        try {
            FileCopyUtils.copy(content, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(attachDTO);
    }

}
