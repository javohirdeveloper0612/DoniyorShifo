package com.example.service;

import com.example.dto.doctor.DoctorCreationDTO;
import com.example.dto.doctor.DoctorResponseDTO;
import com.example.dto.doctor.DoctorUpdateDTO;
import com.example.entity.AttachEntity;
import com.example.entity.DoctorEntity;
import com.example.enums.DoctorRole;
import com.example.enums.Language;
import com.example.exp.attach.FileNotFoundException;
import com.example.exp.doctor.DoctorNotFoundException;
import com.example.exp.doctor.DoctorNotFoundListException;
import com.example.repository.AttachmentRepository;
import com.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorTashkentService {

    private final DoctorRepository doctorRepository;
    private final AttachmentRepository attachmentRepository;
    private final ResourceBundleService resourceBundleService;

    @Autowired
    public DoctorTashkentService(DoctorRepository doctorRepository, AttachmentRepository attachmentRepository, ResourceBundleService resourceBundleService) {
        this.doctorRepository = doctorRepository;
        this.attachmentRepository = attachmentRepository;
        this.resourceBundleService = resourceBundleService;
    }

    /**
     * this method is used to store Tashkent doctors created by ADMIN this method also
     * checks the doctor's image id if the image is not found it throws FileNotFoundException
     * if doctor saves it returns DoctorDTO
     *
     * @param dto      DoctorDTO
     * @param language Language
     * @return ResponseEntity.ok(toDTO.toDoctorDTO ( doctorEntity))
     */

    public DoctorResponseDTO create(DoctorCreationDTO dto, Language language) {

        Optional<AttachEntity> optional = attachmentRepository.findById(dto.getPhotoId());

        if (optional.isEmpty()) {
            throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));
        }

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

        doctorRepository.save(doctorEntity);

        return toResponseDTO(doctorEntity);

    }

    /**
     * this method, the doctor id number is entered by the ADMIN and this id is
     * used to wrap the doctor number in the DTO, if the given doctor id number
     * is not found, a DoctorNotFound Exception is thrown.
     *
     * @param id       Integer
     * @param language Language
     * @return doctorDTO
     */

    public DoctorResponseDTO getDoctorById(Integer id, Language language) {

        Optional<DoctorEntity> optional = doctorRepository.findById(id);

        if (optional.isEmpty()) {
            throw new DoctorNotFoundException(resourceBundleService.getMessage("doctor.not.found.id", language));
        }

        DoctorEntity doctorEntity = optional.get();

        DoctorResponseDTO doctorDTO = new DoctorResponseDTO();


        doctorDTO.setId(doctorEntity.getId());
        doctorDTO.setFirstName_uz(doctorEntity.getFirstName_uz());
        doctorDTO.setLastName_uz(doctorEntity.getLastName_uz());
        doctorDTO.setSpeciality_uz(doctorEntity.getSpeciality_uz());

        doctorDTO.setFirstName_ru(doctorEntity.getFirstName_ru());
        doctorDTO.setLastName_ru(doctorEntity.getLastName_ru());
        doctorDTO.setSpeciality_ru(doctorEntity.getSpeciality_ru());

        doctorDTO.setPhone(doctorEntity.getPhone());
        doctorDTO.setExperience(doctorEntity.getExperience());
        doctorDTO.setDescription_uz(doctorEntity.getDescription_uz());
        doctorDTO.setDescription_ru(doctorEntity.getDescription_ru());
        doctorDTO.setPhotoId(doctorEntity.getPhotoId().getId());

        return doctorDTO;
    }

    /**
     * this method is used by ADMIN to update the doctor data of Tashkent doctor
     * by id number if there is no such id number doctor throws DoctorNotFoundException
     * if the data is updated it returns true
     *
     * @param id        Integer
     * @param doctorDTO DoctorDTO
     * @param language  Language
     * @return true
     */

    public DoctorResponseDTO update(Integer id, DoctorUpdateDTO doctorDTO, Language language) {

        Optional<DoctorEntity> optional = doctorRepository.findById(id);

        if (optional.isEmpty()) {
            throw new DoctorNotFoundException(resourceBundleService.getMessage("doctor.not.found.id", language));
        }

        DoctorEntity doctorEntity = optional.get();
        doctorEntity.setFirstName_uz(doctorDTO.getFirstName_uz());
        doctorEntity.setFirstName_ru(doctorDTO.getFirstName_ru());
        doctorEntity.setLastName_uz(doctorDTO.getLastName_uz());
        doctorEntity.setLastName_ru(doctorDTO.getLastName_ru());
        doctorEntity.setSpeciality_uz(doctorDTO.getSpeciality_uz());
        doctorEntity.setSpeciality_ru(doctorDTO.getSpeciality_ru());
        doctorEntity.setPhone(doctorDTO.getPhone());
        doctorEntity.setExperience(doctorDTO.getExperience());
        doctorEntity.setDescription_uz(doctorDTO.getDescription_uz());
        doctorEntity.setDescription_ru(doctorDTO.getDescription_ru());

        doctorRepository.save(doctorEntity);

        return toResponseDTO(doctorEntity);
    }

    /**
     * this method is used by ADMIN to delete Tashkent doctor by id
     * number if doctor id number is not found DoctorNotFoundException
     * is thrown if doctor is deleted return true
     *
     * @param id       Integer
     * @param language Language
     * @return true
     */

    public String delete(Integer id, Language language) {

        Optional<DoctorEntity> optional = doctorRepository.findById(id);

        if (optional.isEmpty()) {
            throw new DoctorNotFoundException(resourceBundleService.getMessage("doctor.not.found.id", language));
        }

        doctorRepository.deleteById(optional.get().getId());

        return "The doctor is deleted";
    }

    /**
     * this method is used to get the list of Tashkent Doctors in
     * Pageable method if there is no list of doctors DoctorNotFoundListException
     * is thrown if there is the list goes
     *
     * @param page     int
     * @param size     int
     * @param language Language
     * @return PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
     */

    public Page<DoctorResponseDTO> getDoctorPage(int page, int size, Language language) {

        List<DoctorResponseDTO> dtoList = new LinkedList<>();

        Pageable pageable = PageRequest.of(page, size);

        Page<DoctorEntity> entityPage = doctorRepository.findAllByRole(pageable, DoctorRole.ROLE_DOCTOR_TASHKENT);

        if (entityPage.isEmpty()) {
            throw new DoctorNotFoundListException(resourceBundleService.getMessage("doctor.not.found.list", language));
        }


        for (DoctorEntity doctorEntity : entityPage.getContent()) {

            DoctorResponseDTO doctorDTO = new DoctorResponseDTO();
            doctorDTO.setId(doctorEntity.getId());
            doctorDTO.setFirstName_uz(doctorEntity.getFirstName_uz());
            doctorDTO.setLastName_uz(doctorEntity.getLastName_uz());
            doctorDTO.setSpeciality_uz(doctorEntity.getSpeciality_uz());
            doctorDTO.setFirstName_ru(doctorEntity.getFirstName_ru());
            doctorDTO.setLastName_ru(doctorEntity.getLastName_ru());
            doctorDTO.setSpeciality_ru(doctorEntity.getSpeciality_ru());
            doctorDTO.setPhone(doctorEntity.getPhone());
            doctorDTO.setExperience(doctorEntity.getExperience());
            doctorDTO.setDescription_uz(doctorEntity.getDescription_uz());
            doctorDTO.setDescription_ru(doctorEntity.getDescription_ru());
            doctorDTO.setPhotoId(doctorEntity.getPhotoId().getId());
            dtoList.add(doctorDTO);
        }

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    /**
     * this method is used to wrap the dr data in the DataBase back into a DTO
     *
     * @param doctorEntity DoctorEntity
     * @return doctorResponseDTO
     */

    public DoctorResponseDTO toResponseDTO(DoctorEntity doctorEntity) {

        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        doctorResponseDTO.setId(doctorEntity.getId());
        doctorResponseDTO.setFirstName_uz(doctorEntity.getFirstName_uz());
        doctorResponseDTO.setFirstName_ru(doctorEntity.getFirstName_ru());
        doctorResponseDTO.setLastName_uz(doctorEntity.getLastName_uz());
        doctorResponseDTO.setLastName_ru(doctorEntity.getLastName_ru());
        doctorResponseDTO.setSpeciality_uz(doctorEntity.getSpeciality_uz());
        doctorResponseDTO.setSpeciality_ru(doctorEntity.getSpeciality_ru());
        doctorResponseDTO.setPhone(doctorEntity.getPhone());
        doctorResponseDTO.setExperience(doctorEntity.getExperience());
        doctorResponseDTO.setDescription_uz(doctorEntity.getDescription_uz());
        doctorResponseDTO.setDescription_ru(doctorEntity.getDescription_ru());
        doctorResponseDTO.setPhotoId(doctorEntity.getPhotoId().getId());

        return doctorResponseDTO;
    }

    public List<DoctorResponseDTO> getAllList(Language language) {
        List<DoctorEntity> list = doctorRepository.findAllByRole(DoctorRole.ROLE_DOCTOR_TASHKENT);

        if (list.isEmpty()) {
            throw new DoctorNotFoundException(resourceBundleService.getMessage("doctor.not.found.id", language));
        }

        List<DoctorResponseDTO> dtoList = new LinkedList<>();
        list.forEach(doctorEntity -> dtoList.add(getDto(doctorEntity)));

        return dtoList;
    }

    public DoctorResponseDTO getDto(DoctorEntity doctorEntity ) {
        DoctorResponseDTO doctorDTO = new DoctorResponseDTO();


        doctorDTO.setId(doctorEntity.getId());
        doctorDTO.setFirstName_uz(doctorEntity.getFirstName_uz());
        doctorDTO.setLastName_uz(doctorEntity.getLastName_uz());
        doctorDTO.setSpeciality_uz(doctorEntity.getSpeciality_uz());
        doctorDTO.setFirstName_ru(doctorEntity.getFirstName_ru());
        doctorDTO.setLastName_ru(doctorEntity.getLastName_ru());
        doctorDTO.setSpeciality_ru(doctorEntity.getSpeciality_ru());

        doctorDTO.setPhone(doctorEntity.getPhone());
        doctorDTO.setExperience(doctorEntity.getExperience());
        doctorDTO.setDescription_uz(doctorEntity.getDescription_uz());
        doctorDTO.setDescription_ru(doctorEntity.getDescription_ru());
        doctorDTO.setPhotoId(doctorEntity.getPhotoId().getId());

        return doctorDTO;
    }


}
