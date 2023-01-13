package com.example.service;

import com.example.dto.ServicesDTO;
import com.example.entity.ServicesEntity;
import com.example.enums.Language;
import com.example.exp.services.AlreadyExistsServicesException;
import com.example.exp.services.ServicesNotFoundException;
import com.example.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    private final ServicesRepository repository;

    private final ResourceBundleService resourceBundleService;

    public ServicesService(ServicesRepository repository, ResourceBundleService resourceBundleService) {
        this.repository = repository;
        this.resourceBundleService = resourceBundleService;
    }

    public ServicesDTO create(ServicesDTO dto, Language language) {
        Optional<ServicesEntity> optional =repository.findByNameUz(dto.getNameUz());
        if (optional.isPresent()){
            throw new AlreadyExistsServicesException(resourceBundleService.getMessage("already.exists.services",language));
        }
        System.out.println(dto.getNameRu());
        ServicesEntity entity = new ServicesEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        repository.save(entity);

        return getDTO(entity);
    }

    public ServicesDTO getDTO (ServicesEntity entity){
        ServicesDTO dto= new ServicesDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        return dto;
    }

    public ServicesDTO getById(Integer id, Language language) {
        Optional<ServicesEntity> optional = repository.findById(id);

        if (optional.isEmpty()){
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found",language));
        }

        ServicesEntity entity = optional.get();
        ServicesDTO dto = new ServicesDTO();
        if (language.equals(Language.UZ)){
            dto.setNameUz(entity.getNameUz());
        }else if (language.equals(Language.RU)){
            dto.setNameRu(entity.getNameRu());
        }
        dto.setId(entity.getId());
        return dto;
    }

    public List<ServicesDTO> getList(Language language) {
        List<ServicesEntity> servicesList = repository.findAll();
        if (servicesList.isEmpty()){
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found",language));
        }
        List<ServicesDTO> dtoList = new LinkedList<>();
        ServicesDTO dto = new ServicesDTO();
        if (language.equals(Language.UZ)){
            for (ServicesEntity entity : servicesList) {
                dto.setNameUz(entity.getNameUz());
                dtoList.add(dto);
            }
        } else if (language.equals(Language.RU)) {
            for (ServicesEntity entity : servicesList) {
                dto.setNameRu(entity.getNameRu());
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    public String deleteById(Integer id, Language language) {
        Optional<ServicesEntity> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found",language));
        }
        repository.delete(optional.get());

        return "Deleted successfully";
    }
}
