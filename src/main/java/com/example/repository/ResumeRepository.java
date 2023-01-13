package com.example.repository;

import com.example.entity.AttachEntity;
import com.example.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Integer> {
    Optional<AttachEntity> findByAttachId(Integer attach_id);
}
