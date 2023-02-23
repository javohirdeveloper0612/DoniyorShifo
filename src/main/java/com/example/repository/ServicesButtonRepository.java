package com.example.repository;

import com.example.entity.ServicesButtonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesButtonRepository extends JpaRepository<ServicesButtonEntity,Integer> {

    Optional<ServicesButtonEntity> findByButtonNameUzAndButtonNameRu(String nameUz,String nameRu);

    List<ServicesButtonEntity> findAllByServicesId(Integer services_id);

}
