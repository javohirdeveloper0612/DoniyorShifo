package com.example.service;

import com.example.dto.attach.AttachResponseDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesButtonService {

    private final ServicesButtonRepository repository;

    private final ServicesRepository servicesRepository;

    private final ResourceBundleService resourceBundleService;
    private final AttachService attachService;


    public ServicesButtonService(ServicesButtonRepository repository, ServicesRepository servicesRepository, ResourceBundleService resourceBundleService, AttachService attachService) {
        this.repository = repository;
        this.servicesRepository = servicesRepository;
        this.resourceBundleService = resourceBundleService;
        this.attachService = attachService;
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

        AttachResponseDTO attachDTO = attachService.uploadFile(dto.getFile());

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

        System.out.println(entity.getServices());

        ButtonResponseDTO dto1 = new ButtonResponseDTO();
        dto1.setId(entity.getId());
        dto1.setButtonNameUz(entity.getButtonNameUz());
        dto1.setButtonNameRu(entity.getButtonNameRu());
        dto1.setTitleUz(entity.getTitleUz());
        dto1.setTitleRu(entity.getTitleRu());
        dto1.setDescriptionUz(entity.getButtonDescriptionUz());
        dto1.setDescriptionRu(entity.getButtonDescriptionRu());
        dto1.setPhotoUrl(UrlUtil.url + entity.getAttachId());
        dto1.setServices(entity.getServices());
        return dto1;

    }

    public String deleteById(Integer id, Language language) {

        Optional<ServicesButtonEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }
        attachService.deleteById(optional.get().getAttachId());
        repository.delete(optional.get());
        return "Deleted successfully";
    }

    public ButtonResponseDTO geById(Integer id, Language language) {

        Optional<ServicesButtonEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }

        return getDTOByLang(optional.get(), language);
    }



    public ButtonResponseDTO updateById(Integer id, ButtonUpdateDTO dto, Language language) {
        Optional<ServicesButtonEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ButtonNotFoundException(resourceBundleService.getMessage("button.not.found", language));
        }
        ServicesButtonEntity entity = optional.get();

        String attachId = entity.getAttachId();

        AttachResponseDTO attachDTO = attachService.uploadFile(dto.getFile());

        entity.setButtonNameUz(dto.getButtonNameUz());
        entity.setButtonNameRu(dto.getButtonNameRu());
        entity.setTitleUz(dto.getTitleUz());
        entity.setTitleRu(dto.getTitleRu());
        entity.setButtonDescriptionUz(dto.getButtonNameUz());
        entity.setButtonDescriptionRu(dto.getButtonNameRu());
        entity.setAttachId(attachDTO.getId());

        repository.save(entity);

        attachService.updateById(attachId);


        return getDTO(entity);
    }

    public List<ButtonResponseDTO> getListButton(Integer services_id, Language language) {


        List<ServicesButtonEntity> list = repository.findAllByServicesId(services_id);
        if (list.isEmpty()) {
            throw new ButtonNotExistsException(resourceBundleService.getMessage("button.not.exists.found", language));
        }

        List<ButtonResponseDTO> dtoList = new ArrayList<>();

        for (ServicesButtonEntity buttonEntity : list) {
            dtoList.add(getDTOByLang(buttonEntity, language));
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
        dto.setPhotoUrl(UrlUtil.url + entity.getAttachId());
        dto.setServicesId(entity.getServicesId());
        return dto;
    }

    public ButtonResponseDTO getDTOByLang(ServicesButtonEntity entity, Language language) {
        ButtonResponseDTO dto = new ButtonResponseDTO();
        dto.setId(entity.getId());
        dto.setServicesId(entity.getServicesId());
        dto.setPhotoUrl(UrlUtil.url + entity.getAttachId());

        if (language.equals(Language.UZ)) {
            dto.setButtonNameUz(entity.getButtonNameUz());
            dto.setDescriptionUz(entity.getButtonDescriptionUz());
            dto.setTitleUz(entity.getTitleUz());
        } else {
            dto.setButtonNameRu(entity.getButtonNameRu());
            dto.setDescriptionRu(entity.getButtonDescriptionRu());
            dto.setTitleRu(entity.getTitleRu());
        }
        return dto;
    }
}
