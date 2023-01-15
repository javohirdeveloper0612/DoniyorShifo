package com.example.repository;

import com.example.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    @Query("from PatientEntity order by createdDate desc ")
    List<PatientEntity> getAllPatient();

}
