package com.example.service;
import com.example.dto.DoctorDTO;
import com.example.entity.AttachEntity;
import com.example.entity.DoctorEntity;
import com.example.enums.DoctorRole;
import com.example.enums.Language;

import com.example.exp.attach.FileNotFoundException;
import com.example.exp.doctor.DoctorNotFoundException;
import com.example.repository.AttachmentRepository;
import com.example.repository.DoctorRepostoriy;
import com.example.util.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DoctorTashkentService {

    private final DoctorRepostoriy doctorRepostoriy;
    private final AttachmentRepository attachmentRepository;
    private final ResourceBundleService resourceBundleService;
    private final ToDTO toDTO;

    @Autowired
    public DoctorTashkentService(DoctorRepostoriy doctorRepostoriy, AttachmentRepository attachmentRepository, ResourceBundleService resourceBundleService, ToDTO toDTO) {
        this.doctorRepostoriy = doctorRepostoriy;
        this.attachmentRepository = attachmentRepository;
        this.resourceBundleService = resourceBundleService;
        this.toDTO = toDTO;
    }

    /**
     * this method is used to store Tashkent doctors created by ADMIN this method also
     * checks the doctor's image id if the image is not found it throws FileNotFoundException
     * if doctor saves it returns DoctorDTO
     *
     * @param photoId  Integer
     * @param dto      DoctorDTO
     * @param language Language
     * @return DoctorDTO
     */

    public ResponseEntity<DoctorDTO> create(Integer photoId, DoctorDTO dto, Language language) {

        Optional<AttachEntity> optional = attachmentRepository.findById(photoId);
        if (optional.isEmpty()) throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));

        AttachEntity attach = optional.get();
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName_uz(dto.getFirstName_uz());
        doctorEntity.setFirstName_ru(dto.getFirstName_ru());
        doctorEntity.setLastName_uz(dto.getLastName_uz());
        doctorEntity.setLastName_ru(dto.getLastName_ru());
        doctorEntity.setSpeciality_uz(dto.getSpeciality_uz());
        doctorEntity.setSpeciality_ru(dto.getSpeciality_ru());
        doctorEntity.setPhone(dto.getPhone());
        doctorEntity.setExperience(dto.getExperience());
        doctorEntity.setDescription_uz(dto.getDescription_uz());
        doctorEntity.setDescription_ru(dto.getDescription_ru());
        doctorEntity.setPhotoId(attach);
        doctorEntity.setRole(DoctorRole.ROLE_DOCTOR_TASHKENT);
        doctorRepostoriy.save(doctorEntity);
        dto.setId(doctorEntity.getId());
        return ResponseEntity.ok(toDTO.toDoctorDto(doctorEntity));
    }

    /**
     * this method, the doctor id number is entered by the ADMIN and this id is
     * used to wrap the doctor number in the DTO, if the given doctor id number
     * is not found, a DoctorNotFound Exception is thrown.
     *
     * @param id       Integer
     * @param language Language
     * @return DoctorDTO
     */

    public ResponseEntity<DoctorDTO> getDoctorById(Integer id, Language language) {

        Optional<DoctorEntity> optional = doctorRepostoriy.findById(id);
        if (optional.isEmpty()){
            throw new DoctorNotFoundException(resourceBundleService.getMessage("doctor.not.found.id", language));
        }

        DoctorEntity doctorEntity = optional.get();
        return ResponseEntity.ok(toDTO.toDoctorDto(doctorEntity));
    }
}
