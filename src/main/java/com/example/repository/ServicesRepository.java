package com.example.repository;

import com.example.entity.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicesRepository extends JpaRepository<ServicesEntity,Integer> {

    Optional<ServicesEntity> findByNameUzAndNameRu(String nameUz,String nameRu);



}
