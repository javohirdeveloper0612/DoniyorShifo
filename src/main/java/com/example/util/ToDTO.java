package com.example.util;

import com.example.dto.DoctorDTO;
import com.example.dto.ResumeDto;
import com.example.entity.DoctorEntity;
import com.example.entity.ResumeEntity;
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

    public ResumeDto toResumeDto(ResumeEntity resumeEntity) {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setFullName(resumeEntity.getFullName());
        resumeDto.setPhone(resumeEntity.getPhone());
        resumeDto.setEmail(resumeEntity.getEmail());
        resumeDto.setDescription(resumeEntity.getDescription());
        resumeDto.setId(resumeEntity.getId());
        resumeDto.setFileId(resumeEntity.getAttach().getId());
        return resumeDto;
    }

}
