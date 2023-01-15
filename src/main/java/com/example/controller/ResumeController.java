package com.example.controller;
import com.example.dto.ResumeDto;
import com.example.enums.Language;
import com.example.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
     * This method is used for Resume Data .If saves this data return ResumeDto
     *
     * @param id        Integer
     * @param resumeDto ResumeDto
     * @return ResumeDto
     */
    @PostMapping("/saveResume/{id}")
    @Operation(summary = "Saving resume method", description = "This method is used for saving resume data")
    public ResponseEntity<?> saveResume(@PathVariable Integer id, @Valid @RequestBody ResumeDto resumeDto, Language language) {
        return resumeService.saveResume(id, resumeDto, language);
    }


    /**
     * This method is used for getting resume data by id
     *
     * @param id Integer
     * @return ResumeDto
     */
    @GetMapping("/getResume/{id}")
    @Operation(summary = "getting resume data method", description = "This method is used for getting resume data by id")
    public ResponseEntity<?> getResumeById(@PathVariable Integer id) {
        return resumeService.getResumeById(id);
    }


}
