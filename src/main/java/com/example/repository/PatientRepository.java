package com.example.repository;

import com.example.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    @Query("from PatientEntity order by createdDate desc ")
    List<PatientEntity> getAllPatient();

}
