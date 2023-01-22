package com.example.controller;

import com.example.dto.resume.CreatedResumeDto;
import com.example.enums.Language;
import com.example.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
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
    @PostMapping("/public/save_resume")
    @Operation(summary = "Saving resume method", description = "This method is used for saving resume data")
    public ResponseEntity<?> saveResume(@Valid @RequestBody CreatedResumeDto resumeDto) {
        return resumeService.saveResume(resumeDto, Language.UZ);
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
    public ResponseEntity<?> getResumeById(@PathVariable Integer id) {
        return resumeService.getResumeById(id, Language.UZ);
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
