package com.example.repository;

import com.example.entity.AttachContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachContentEntity, Integer> {
    Optional<AttachContentEntity> findByAttachId(Integer attach_id);
}
