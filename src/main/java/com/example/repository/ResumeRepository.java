package com.example.repository;

import com.example.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Integer> {

    @Query("from ResumeEntity order by createdAt desc ")
    List<ResumeEntity> findAllResume();
}
