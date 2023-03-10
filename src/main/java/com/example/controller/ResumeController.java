package com.example.controller;

import com.example.dto.resume.CreatedResumeDto;
import com.example.enums.Language;
import com.example.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/resume")
@Tag(name = "Resume Controller", description = "This controller gets resume data")
public class ResumeController {


    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }


    /**
     * This method is used for Resume Data .If saves this data return CreatedResumeDto
     *
     * @param resumeDto CreatedResumeDto
     * @return CreatedResumeDto
     */
    @PostMapping(value = "/public/save_resume",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Saving resume method", description = "This method is used for saving resume data")
    public ResponseEntity<?> saveResume(@Valid @ModelAttribute CreatedResumeDto resumeDto,
                                        @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        log.info("save resume ownerResume phone{} , Name{}", resumeDto.getPhone(),
                resumeDto.getFullName());
        return resumeService.saveResume(resumeDto, language);
    }


    /**
     * This method is used for getting resume data by id
     *
     * @param id Integer
     * @return CreatedResumeDto
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/view_resume/{id}")
    @Operation(summary = "getting resume data method", description = "This method is used for getting resume data by id")
    public ResponseEntity<?> getResumeById(@PathVariable Integer id,
                                           @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        log.info("get resume By id{}", id);
        return resumeService.getResumeById(id, language);
    }


    /**
     * This method is used for getting all the resume data from DB
     *
     * @return List<ResumeEntity> </>
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/view_all")
    @Operation(summary = "Getting all the resume data", description = "This method is used for getting all the resume data")
    public ResponseEntity<?> getAllResume() {
        return resumeService.getAllResume();
    }
}
