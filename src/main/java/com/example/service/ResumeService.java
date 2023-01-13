package com.example.service;

import com.example.dto.ResumeDto;
import com.example.entity.AttachEntity;
import com.example.entity.ResumeEntity;
import com.example.enums.Language;
import com.example.exp.FileNotFoundException;
import com.example.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final ResourceBundleService resourceBundleService;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, ResourceBundleService resourceBundleService) {
        this.resumeRepository = resumeRepository;
        this.resourceBundleService = resourceBundleService;
    }


    /**
     * This method  is used for saving resume file and data
     *
     * @param id        Integer
     * @param resumeDto ResumeDto
     * @param language  Language
     * @return ResumeDto
     */
    public ResponseEntity<?> saveResume(Integer id, ResumeDto resumeDto, Language language) {

        Optional<AttachEntity> optional = resumeRepository.findByAttachId(id);
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

        return ResponseEntity.ok(savedResume);
    }


    /**
     * This method is used for getting resume data by id
     *
     * @param id Integer
     * @return ResumeDto
     */
    public ResponseEntity<?> getResumeById(Integer id) {

        Optional<ResumeEntity> optional = resumeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new FileNotFoundException("Resume data  not founded");
        }


        ResumeEntity resumeEntity = optional.get();

        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setFullName(resumeEntity.getFullName());
        resumeDto.setPhone(resumeEntity.getPhone());
        resumeDto.setEmail(resumeEntity.getEmail());
        resumeDto.setDescription(resumeEntity.getDescription());

        return ResponseEntity.ok(resumeDto);
    }
}
