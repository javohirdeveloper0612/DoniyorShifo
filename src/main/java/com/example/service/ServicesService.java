package com.example.service;

import com.example.dto.services.ServicesResponseDTO;
import com.example.dto.services.ServicesCreateDTO;
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

    public ServicesResponseDTO create(ServicesCreateDTO dto, Language language) {
        Optional<ServicesEntity> optional = repository.findByNameUzAndNameRu(dto.getNameUz(), dto.getNameRu());
        if (optional.isPresent()) {
            throw new AlreadyExistsServicesException(resourceBundleService.getMessage("already.exists.services", language));
        }
        ServicesEntity entity = new ServicesEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        repository.save(entity);

        return getDTO(entity);
    }

    public ServicesResponseDTO getDTO(ServicesEntity entity) {
        ServicesResponseDTO dto = new ServicesResponseDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        return dto;
    }

    public ServicesResponseDTO getById(Integer id, Language language) {
        Optional<ServicesEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found", language));
        }
        ServicesEntity entity = optional.get();
        return getDTO(entity);
    }

    public List<ServicesResponseDTO> getList() {
        List<ServicesEntity> servicesList = repository.findAll();
        if (servicesList.isEmpty()) {
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found", Language.UZ));
        }
        List<ServicesResponseDTO> dtoList = new LinkedList<>();
        for (ServicesEntity entity : servicesList) {
            dtoList.add(getDTO(entity));
        }
        return dtoList;
    }

    public String deleteById(Integer id, Language language) {
        Optional<ServicesEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found", language));
        }

        repository.deleteById(optional.get().getId());

        return "Deleted successfully";
    }

    public ServicesResponseDTO updateById(Integer id, ServicesCreateDTO dto, Language language) {
        Optional<ServicesEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ServicesNotFoundException(resourceBundleService.getMessage("services.not.found", language));
        }

        ServicesEntity entity = optional.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());

        repository.save(entity);

        return getDTO(entity);
    }


}
