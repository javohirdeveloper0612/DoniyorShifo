package com.example.entity;

import com.example.enums.DoctorRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName_uz;

    @Column(nullable = false)
    private String firstName_ru;

    @Column(nullable = false)
    private String lastName_uz;

    @Column(nullable = false)
    private String lastName_ru;

    @Column(nullable = false)
    private String speciality_uz;

    @Column(nullable = false)
    private String speciality_ru;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private Integer experience;

    @Column(columnDefinition = "text", nullable = false)
    private String description_uz;

    @Column(columnDefinition = "text", nullable = false)
    private String description_ru;
    @Column(name = "photo_id")
    private String photoId;
    @OneToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity photo;


    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "doctor_role")
    private DoctorRole role;


}
