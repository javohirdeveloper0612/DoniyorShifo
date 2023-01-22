package com.example.repository;
import com.example.entity.DoctorEntity;
import com.example.enums.DoctorRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface DoctorRepository extends PagingAndSortingRepository<DoctorEntity,Integer>, JpaRepository<DoctorEntity,Integer> {

    Page<DoctorEntity> findAllByRole(Pageable pageable,DoctorRole role);
    List<DoctorEntity> findAllByRole(DoctorRole role);

}
