package com.example.service;

import com.example.dto.services_data.DataUpdateDTO;
import com.example.dto.services_data.ServicesCreateDataDTO;
import com.example.dto.services_data.ServicesDataResponseDTO;
import com.example.entity.AttachEntity;
import com.example.entity.ServicesButtonEntity;
import com.example.entity.ServicesDataEntity;
import com.example.enums.Language;
import com.example.exp.attach.AttachNotFoundException;
import com.example.exp.servicesButton.ButtonNotFoundException;
import com.example.exp.services_data.ServicesDataNotFoundException;
import com.example.repository.AttachmentRepository;
import com.example.repository.ServicesButtonRepository;
import com.example.repository.ServicesDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServicesDataService {
    private final ServicesDataRepository repository;

    private final AttachmentRepository attachRepository;

    private final ServicesButtonRepository buttonRepository;

    private final ResourceBundleService resourceBundleService;

    @Autowired
    public ServicesDataService(ServicesDataRepository repository, AttachmentRepository attachRepository, ServicesButtonRepository buttonRepository, ResourceBundleService resourceBundleService) {
        this.repository = repository;
        this.attachRepository = attachRepository;
        this.buttonRepository = buttonRepository;
        this.resourceBundleService = resourceBundleService;
    }


    public ServicesDataResponseDTO create(ServicesCreateDataDTO dto, Language language) {
        Optional<AttachEntity> attach = attachRepository.findById(dto.getAttachId());
        if (attach.isEmpty()) {
            throw new AttachNotFoundException(resourceBundleService.getMessage("attach.not.found", language));
        }

        Optional<ServicesButtonEntity> button = buttonRepository.findById(dto.getButtonId());
        if (button.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }

        ServicesDataEntity entity = new ServicesDataEntity();
        entity.setTitleUz(dto.getTitleUz());
        entity.setTitleRu(dto.getTitleRu());
        entity.setDescriptionUz(dto.getDescriptionUz());
        entity.setDescriptionRu(dto.getDescriptionRu());
        entity.setButtonId(dto.getButtonId());
        entity.setAttachId(dto.getAttachId());
        entity.setCreatedDate(LocalDateTime.now());

        repository.save(entity);

        return getDTO(entity);
    }

    private ServicesDataResponseDTO getDTO(ServicesDataEntity entity) {
        ServicesDataResponseDTO dto = new ServicesDataResponseDTO();
        dto.setId(entity.getId());
        dto.setTitleRu(entity.getTitleRu());
        dto.setTitleUz(entity.getTitleUz());
        dto.setDescriptionRu(entity.getDescriptionRu());
        dto.setDescriptionUz(entity.getDescriptionUz());
        dto.setAttachId(entity.getAttachId());
        dto.setButtonId(entity.getButtonId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public ServicesDataResponseDTO getById(Integer id, Language language) {
        Optional<ServicesDataEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ServicesDataNotFoundException(resourceBundleService.getMessage("services.data.not.found", language));
        }

        return getDTO(optional.get());
    }

    public String deleteById(Integer id, Language language) {

        Optional<ServicesDataEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ServicesDataNotFoundException(resourceBundleService.getMessage("services.data.not.found", language));
        }
        repository.deleteById(optional.get().getId());

        return "Deleted successfully";
    }



    public ServicesDataResponseDTO updateById(Integer id, DataUpdateDTO dto, Language language) {

        Optional<ServicesDataEntity> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new ServicesDataNotFoundException(resourceBundleService.getMessage("services.data.not.found", language));
        }

        ServicesDataEntity entity = optional.get();
        entity.setTitleUz(dto.getTitleUz());
        entity.setTitleRu(dto.getTitleRu());
        entity.setDescriptionUz(dto.getDescriptionUz());
        entity.setDescriptionRu(dto.getDescriptionRu());

        repository.save(entity);

        return getDTO(entity);
    }
}
