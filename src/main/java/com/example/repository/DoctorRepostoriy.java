package com.example.repository;
import com.example.entity.AttachEntity;
import com.example.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface DoctorRepostoriy extends CrudRepository<DoctorEntity,Integer> , JpaRepository<DoctorEntity,Integer> {

    Optional<AttachEntity> findByPhotoId(Integer photoId);

}
