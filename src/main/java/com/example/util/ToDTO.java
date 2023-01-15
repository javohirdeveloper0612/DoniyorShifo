package com.example.util;
import com.example.dto.doctor.DoctorResponseDTO;
import com.example.entity.DoctorEntity;
import org.springframework.stereotype.Component;

@Component
public class ToDTO {


    public DoctorResponseDTO toDoctorDTO(DoctorEntity doctorEntity){
        DoctorResponseDTO doctorDTO = new DoctorResponseDTO();
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





}
