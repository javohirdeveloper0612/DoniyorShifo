package com.example.util;

import com.example.dto.news.ResponseNewsDto;
import com.example.dto.patient.ResponsePatientDto;
import com.example.dto.resume.ResponseResumeDto;
import com.example.entity.NewsEntity;
import com.example.entity.PatientEntity;
import com.example.entity.ResumeEntity;
import org.springframework.stereotype.Component;

@Component
public class ToDTO {




    public ResponseResumeDto toResponseResumeDto(ResumeEntity resumeEntity) {
        ResponseResumeDto resumeDto = new ResponseResumeDto();
        resumeDto.setId(resumeEntity.getId());
        resumeDto.setFullName(resumeEntity.getFullName());
        resumeDto.setPhone(resumeEntity.getPhone());
        resumeDto.setEmail(resumeEntity.getEmail());
        resumeDto.setDescription(resumeEntity.getDescription());
        resumeDto.setResumeFileId(resumeEntity.getAttach().getId());
        return resumeDto;
    }


    public ResponsePatientDto toResponsePatientDto(PatientEntity patient) {
        ResponsePatientDto patientDto = new ResponsePatientDto();
        patientDto.setId(patient.getId());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setPhone(patient.getPhone());
        patientDto.setDoctorId(patient.getDoctorId().getId());
        return patientDto;
    }


    public ResponseNewsDto responseNewsDto(NewsEntity newsEntity) {
        ResponseNewsDto newsDto = new ResponseNewsDto();
        newsDto.setId(newsEntity.getId());
        newsDto.setTitle_uz(newsEntity.getTitle_uz());
        newsDto.setTitle_ru(newsEntity.getTitle_ru());
        newsDto.setDescription_ru(newsEntity.getDescription_ru());
        newsDto.setDescription_uz(newsEntity.getDescription_uz());
        return newsDto;
    }





}
