package com.example.service;

import com.example.dto.attach.AttachDTO;
import com.example.dto.services_button.ButtonCreateDTO;
import com.example.dto.services_button.ButtonResponseDTO;
import com.example.dto.services_button.ButtonUpdateDTO;
import com.example.entity.ServicesButtonEntity;
import com.example.entity.ServicesEntity;
import com.example.enums.Language;
import com.example.exp.services.ServicesNotFoundException;
import com.example.exp.servicesButton.AllReadyExistsButtonException;
import com.example.exp.servicesButton.ButtonNotExistsException;
import com.example.exp.servicesButton.ButtonNotFoundException;
import com.example.repository.AttachmentRepository;
import com.example.repository.ServicesButtonRepository;
import com.example.repository.ServicesRepository;
import com.example.util.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesButtonService {

    private final ServicesButtonRepository repository;

    private final ServicesRepository servicesRepository;

    private final ResourceBundleService resourceBundleService;
    private final AttachService attachService;
    private final AttachmentRepository attachmentRepository;

    public ServicesButtonService(ServicesButtonRepository repository, ServicesRepository servicesRepository, ResourceBundleService resourceBundleService, AttachService attachService, AttachmentRepository attachmentRepository) {
        this.repository = repository;
        this.servicesRepository = servicesRepository;
        this.resourceBundleService = resourceBundleService;
        this.attachService = attachService;
        this.attachmentRepository = attachmentRepository;
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

        AttachDTO attachDTO = attachService.uploadFile(dto.getFile());

        ServicesButtonEntity entity = new ServicesButtonEntity();
        entity.setButtonNameUz(dto.getButtonNameUz());
        entity.setButtonNameRu(dto.getButtonNameRu());
        entity.setTitleUz(dto.getTitleUz());
        entity.setTitleRu(dto.getTitleRu());
        entity.setButtonDescriptionUz(dto.getDescriptionUz());
        entity.setButtonDescriptionRu(dto.getDescriptionRu());
        entity.setAttachId(attachDTO.getId());
        entity.setServicesId(dto.getServicesId());


        repository.save(entity);


        return getDTO(entity);
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

        return getDTOByLang(optional.get(),language);
    }



    public ButtonResponseDTO updateById(Integer id, ButtonUpdateDTO dto, Language language) {
        Optional<ServicesButtonEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }
        ServicesButtonEntity entity = optional.get();

        AttachDTO attachDTO = attachService.uploadFile(dto.getFile());

        try {
            attachmentRepository.deleteById(entity.getAttachId());
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        entity.setButtonNameUz(dto.getButtonNameUz());
        entity.setButtonNameRu(dto.getButtonNameRu());
        entity.setTitleUz(dto.getTitleUz());
        entity.setTitleRu(dto.getTitleRu());
        entity.setButtonDescriptionUz(dto.getButtonNameUz());
        entity.setButtonDescriptionRu(dto.getButtonNameRu());
        entity.setAttachId(attachDTO.getId());

        repository.save(entity);

        return getDTO(entity);
    }

    public List<ButtonResponseDTO> getListButton(Language language) {


        List<ServicesButtonEntity> list = repository.findAll();
        if (list.isEmpty()) {
            throw new ButtonNotExistsException(resourceBundleService.getMessage("button.not.exists.found", language));
        }

        List<ButtonResponseDTO> dtoList = new ArrayList<>();

        for (ServicesButtonEntity buttonEntity : list) {
            dtoList.add(getDTOByLang(buttonEntity,language));
        }

        return dtoList;
    }

    public ButtonResponseDTO getDTO(ServicesButtonEntity entity) {
        ButtonResponseDTO dto = new ButtonResponseDTO();
        dto.setId(entity.getId());
        dto.setButtonNameUz(entity.getButtonNameUz());
        dto.setButtonNameRu(entity.getButtonNameRu());
        dto.setTitleUz(entity.getTitleUz());
        dto.setTitleRu(entity.getTitleRu());
        dto.setDescriptionUz(entity.getButtonDescriptionUz());
        dto.setDescriptionRu(entity.getButtonDescriptionRu());
        dto.setPhotoUrl(UrlUtil.url+entity.getAttachId());
        dto.setServicesId(entity.getServicesId());
        return dto;
    }

    public ButtonResponseDTO getDTOByLang(ServicesButtonEntity entity,Language language) {
        ButtonResponseDTO dto = new ButtonResponseDTO();
        dto.setId(entity.getId());
        dto.setServicesId(entity.getServicesId());
        dto.setPhotoUrl(UrlUtil.url +entity.getAttachId());

        if (language.equals(Language.UZ)){
            dto.setButtonNameUz(entity.getButtonNameUz());
            dto.setDescriptionUz(entity.getButtonDescriptionUz());
            dto.setTitleUz(entity.getTitleUz());
        }else {
            dto.setButtonNameRu(entity.getButtonNameRu());
            dto.setDescriptionRu(entity.getButtonDescriptionRu());
            dto.setTitleRu(entity.getTitleRu());
        }
        return dto;
    }
}
