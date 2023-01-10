package com.example.repository;

import com.example.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByUsernameAndPassword(String username,String password);

    Optional<AdminEntity> findByUsername(String username);

}
