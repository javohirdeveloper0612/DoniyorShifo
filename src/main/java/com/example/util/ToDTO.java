package com.example.util;

import com.example.dto.DoctorDTO;
import com.example.dto.news.ResponseNewsDto;
import com.example.dto.patient.ResponsePatientDto;
import com.example.dto.resume.ResponseResumeDto;
import com.example.entity.DoctorEntity;
import com.example.entity.NewsEntity;
import com.example.entity.PatientEntity;
import com.example.entity.ResumeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ToDTO {


    public DoctorDTO toDoctorDto(DoctorEntity doctorEntity) {

        DoctorDTO doctorDTO = new DoctorDTO();
        ;
        doctorDTO.setId(doctorEntity.getId());
        doctorDTO.setFirstName_uz(doctorEntity.getFirstName_uz());
        doctorDTO.setFirstName_ru(doctorEntity.getFirstName_ru());
        doctorDTO.setLastName_uz(doctorEntity.getLastName_uz());
        doctorDTO.setLastName_ru(doctorEntity.getLastName_ru());
        doctorDTO.setSpeciality_uz(doctorEntity.getSpeciality_uz());
        doctorDTO.setSpeciality_ru(doctorEntity.getSpeciality_ru());
        doctorDTO.setPhone(doctorEntity.getPhone());
        doctorDTO.setExperience(doctorEntity.getExperience());
        doctorDTO.setDescription_uz(doctorEntity.getDescription_uz());
        doctorDTO.setDescription_ru(doctorEntity.getDescription_ru());
        doctorDTO.setPhotoId(doctorEntity.getPhotoId().getId());
        return doctorDTO;
    }

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
