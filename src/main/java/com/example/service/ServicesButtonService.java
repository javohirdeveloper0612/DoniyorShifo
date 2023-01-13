package com.example.service;

import com.example.dto.ButtonDTO;
import com.example.entity.ServicesButtonEntity;
import com.example.entity.ServicesEntity;
import com.example.enums.Language;
import com.example.exp.services.ServicesNotFoundException;
import com.example.exp.servicesButton.AllReadyExistsButtonException;
import com.example.exp.servicesButton.ButtonNotFoundException;
import com.example.exp.servicesButton.DataInCompleException;
import com.example.repository.ServicesButtonRepository;
import com.example.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServicesButtonService {

    private final ServicesButtonRepository repository;

    private final ServicesRepository servicesRepository;

    private final ResourceBundleService resourceBundleService;

    public ServicesButtonService(ServicesButtonRepository repository, ServicesRepository servicesRepository, ResourceBundleService resourceBundleService) {
        this.repository = repository;
        this.servicesRepository = servicesRepository;
        this.resourceBundleService = resourceBundleService;
    }

    public ButtonDTO create(ButtonDTO dto, Language language) {
        Optional<ServicesButtonEntity> optional = repository.findByButtonNameUzAndButtonNameRu(dto.getButtonNameUz(), dto.getButtonNameRu());
        if (optional.isPresent()) {
            throw new AllReadyExistsButtonException(resourceBundleService.getMessage("button.exists", language));
        }
        Optional<ServicesEntity> services = servicesRepository.findById(dto.getServices());

        if (services.isEmpty()) {
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found", language));
        }

        if (dto.getButtonNameUz() == null || dto.getButtonNameRu() == null || dto.getButtonDescriptionUz() == null || dto.getButtonDescriptionRu() == null) {
            throw new DataInCompleException(resourceBundleService.getMessage("data.incomplete", language));
        }

        ServicesButtonEntity entity = new ServicesButtonEntity();
        entity.setButtonNameUz(dto.getButtonNameUz());
        entity.setButtonNameRu(dto.getButtonNameRu());
        entity.setButtonDescriptionUz(dto.getButtonDescriptionUz());
        entity.setButtonDescriptionRu(dto.getButtonDescriptionRu());
        entity.setServicesId(dto.getServices());
        entity.setCreatedDate(LocalDateTime.now());

        repository.save(entity);


        return getDTO(entity);
    }

    public ButtonDTO getDTO(ServicesButtonEntity entity) {
        ButtonDTO dto = new ButtonDTO();
        dto.setId(entity.getId());
        dto.setButtonNameUz(entity.getButtonNameUz());
        dto.setButtonNameRu(entity.getButtonNameRu());
        dto.setButtonDescriptionUz(entity.getButtonDescriptionUz());
        dto.setButtonDescriptionRu(entity.getButtonDescriptionRu());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public String deleteById(Integer id, Language language) {

        Optional<ServicesButtonEntity> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found",language));
        }
        repository.deleteById(id);
        return "Deleted successfully";
    }
}
