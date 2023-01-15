package com.example.service;

import com.example.dto.resume.CreatedResumeDto;
import com.example.entity.AttachEntity;
import com.example.entity.ResumeEntity;
import com.example.enums.Language;
import com.example.exp.attach.FileNotFoundException;
import com.example.repository.AttachmentRepository;
import com.example.repository.ResumeRepository;
import com.example.util.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final ResourceBundleService resourceBundleService;
    private final AttachmentRepository attachmentRepository;
    private final ToDTO toDTO;


    @Autowired
    public ResumeService(ResumeRepository resumeRepository, ResourceBundleService resourceBundleService,
                         AttachmentRepository attachmentRepository, ToDTO toDTO) {
        this.resumeRepository = resumeRepository;
        this.resourceBundleService = resourceBundleService;
        this.attachmentRepository = attachmentRepository;

        this.toDTO = toDTO;
    }


    /**
     * This method  is used for saving resume file and data
     *
     * @param resumeDto CreatedResumeDto
     * @param language  Language
     * @return CreatedResumeDto
     */
    public ResponseEntity<?> saveResume(CreatedResumeDto resumeDto, Language language) {

        Optional<AttachEntity> optional = attachmentRepository.findById(resumeDto.getFileId());
        if (optional.isEmpty()) {
            throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));
        }
        AttachEntity attach = optional.get();
        ResumeEntity resume = new ResumeEntity();
        resume.setFullName(resumeDto.getFullName());
        resume.setPhone(resumeDto.getPhone());
        resume.setEmail(resumeDto.getEmail());
        resume.setDescription(resumeDto.getDescription());
        resume.setAttach(attach);
        ResumeEntity savedResume = resumeRepository.save(resume);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO.toResponseResumeDto(savedResume));
    }


    /**
     * This method is used for getting resume data by id
     *
     * @param id Integer
     * @return CreatedResumeDto
     */
    public ResponseEntity<?> getResumeById(Integer id, Language language) {

        Optional<ResumeEntity> optional = resumeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));
        }

        ResumeEntity resumeEntity = optional.get();
        return ResponseEntity.ok(toDTO.toResponseResumeDto(resumeEntity));
    }

    /**
     * This method is used for getting all the resume data from DB
     *
     * @return List<ResumeEntity> </>
     */
    public ResponseEntity<?> getAllResume() {
        return ResponseEntity.ok(resumeRepository.findAllResume());
    }


}
