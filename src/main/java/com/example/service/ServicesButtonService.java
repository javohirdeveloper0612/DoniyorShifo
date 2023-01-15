package com.example.service;

import com.example.dto.services_button.ButtonCreateDTO;
import com.example.dto.services_button.ButtonResponseDTO;
import com.example.dto.services_button.ButtonUpdateDTO;
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
import java.util.ArrayList;
import java.util.List;
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

    public ButtonResponseDTO create(ButtonCreateDTO dto, Language language) {
        Optional<ServicesButtonEntity> optional = repository.findByButtonNameUzAndButtonNameRu(dto.getButtonNameUz(), dto.getButtonNameRu());
        if (optional.isPresent()) {
            throw new AllReadyExistsButtonException(resourceBundleService.getMessage("button.exists", language));
        }
        Optional<ServicesEntity> services = servicesRepository.findById(dto.getServicesId());

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
        entity.setServicesId(dto.getServicesId());
        entity.setCreatedDate(LocalDateTime.now());

        repository.save(entity);


        return getDTO(entity);
    }

    public ButtonResponseDTO getDTO(ServicesButtonEntity entity) {
        ButtonResponseDTO dto = new ButtonResponseDTO();
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
        if (optional.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }
        repository.deleteById(id);
        return "Deleted successfully";
    }

    public ButtonResponseDTO geById(Integer id, Language language) {

        Optional<ServicesButtonEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }

        return getDtoByLang(optional.get(), language);
    }

    public ButtonResponseDTO getDtoByLang(ServicesButtonEntity entity, Language language) {
        ButtonResponseDTO dto = new ButtonResponseDTO();
        if (language.equals(Language.UZ)) {

            dto.setButtonNameUz(entity.getButtonNameUz());
            dto.setButtonDescriptionUz(entity.getButtonDescriptionUz());
        } else if (language.equals(Language.RU)) {
            dto.setButtonNameRu(entity.getButtonNameRu());
            dto.setButtonDescriptionRu(entity.getButtonDescriptionRu());
        }
        dto.setId(entity.getId());
        dto.setServicesId(entity.getServicesId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public ButtonResponseDTO updateById(Integer id, ButtonUpdateDTO dto, Language language) {
        Optional<ServicesButtonEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }
        ServicesButtonEntity entity = optional.get();

        entity.setButtonNameUz(dto.getButtonNameUz());
        entity.setButtonNameRu(dto.getButtonNameRu());
        entity.setButtonDescriptionUz(dto.getButtonDescriptionUz());
        entity.setButtonDescriptionRu(dto.getButtonDescriptionRu());

        repository.save(entity);

        return getDTO(entity);
    }

    public List<ButtonResponseDTO> getListButton(Language language) {



        List<ServicesButtonEntity> list = repository.findAll();

        if (list.isEmpty()){
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }


        List<ButtonResponseDTO> dtoList = new ArrayList<>();

        for (ServicesButtonEntity buttonEntity : list) {
            dtoList.add(getDtoByLang(buttonEntity,language));
        }

        return dtoList;
    }
}