package com.example.service;

import com.example.dto.attach.AttachDTO;
import com.example.dto.services_data.DataUpdateDTO;
import com.example.dto.services_data.ServicesCreateDataDTO;
import com.example.dto.services_data.ServicesDataResponseDTO;
import com.example.entity.ServicesButtonEntity;
import com.example.entity.ServicesDataEntity;
import com.example.enums.Language;
import com.example.exp.servicesButton.ButtonNotFoundException;
import com.example.exp.services_data.ServicesDataNotFoundException;
import com.example.repository.ServicesButtonRepository;
import com.example.repository.ServicesDataRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesDataService {
    private final ServicesDataRepository repository;
    private final ServicesButtonRepository buttonRepository;

    private final ResourceBundleService resourceBundleService;

    private final AttachService attachService;

    @Autowired
    public ServicesDataService(ServicesDataRepository repository, ServicesButtonRepository buttonRepository, ResourceBundleService resourceBundleService, AttachService attachService) {
        this.repository = repository;
        this.buttonRepository = buttonRepository;
        this.resourceBundleService = resourceBundleService;
        this.attachService = attachService;
    }


    public ServicesDataResponseDTO create(ServicesCreateDataDTO dto, Language language) {
        Optional<ServicesButtonEntity> button = buttonRepository.findById(dto.getButtonId());
        if (button.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }

        AttachDTO attach = attachService.uploadFile(dto.getFile());

        ServicesDataEntity entity = new ServicesDataEntity();
        entity.setTitleUz(dto.getTitleUz());
        entity.setTitleRu(dto.getTitleRu());
        entity.setDescriptionUz(dto.getDescriptionUz());
        entity.setDescriptionRu(dto.getDescriptionRu());
        entity.setButtonId(dto.getButtonId());
        entity.setAttachId(attach.getId());

        repository.save(entity);

        return getDTO(entity);
    }


    public ServicesDataResponseDTO getById(Integer id, Language language) {
        Optional<ServicesDataEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ServicesDataNotFoundException(resourceBundleService.getMessage("services.data.not.found", language));
        }

        return getDTOByLang(optional.get(), language);
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
        if (optional.isEmpty()) {
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

    private ServicesDataResponseDTO getDTO(ServicesDataEntity entity) {
        ServicesDataResponseDTO dto = new ServicesDataResponseDTO();
        dto.setId(entity.getId());
        dto.setTitleRu(entity.getTitleRu());
        dto.setTitleUz(entity.getTitleUz());
        dto.setDescriptionRu(entity.getDescriptionRu());
        dto.setDescriptionUz(entity.getDescriptionUz());
        dto.setButtonId(entity.getButtonId());
        dto.setPhotoUrl("http://api.doniyor.doniyorshifo.uz/api/attach/public/download/"+entity.getAttachId());

        return dto;
    }

    private ServicesDataResponseDTO getDTOByLang(ServicesDataEntity entity, Language language) {
        ServicesDataResponseDTO dto = new ServicesDataResponseDTO();
        dto.setId(entity.getId());
        if (language.equals(Language.UZ)) {
            dto.setTitleUz(entity.getTitleUz());
            dto.setDescriptionUz(entity.getDescriptionUz());
        } else {
            dto.setTitleRu(entity.getTitleRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }
        dto.setPhotoUrl("http://api.doniyor.doniyorshifo.uz/api/attach/public/download/"+entity.getAttachId());
        dto.setButtonId(entity.getButtonId());

        return dto;
    }
}
