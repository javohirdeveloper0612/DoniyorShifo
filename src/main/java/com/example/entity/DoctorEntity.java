package com.example.entity;

import com.example.enums.DoctorRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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
    private String firsName;

    @Column(nullable = false)
    private String lastName;

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
    @Enumerated(EnumType.STRING)
    @Column
    private DoctorRole role;
    @OneToOne(cascade = CascadeType.PERSIST)
    private AttachEntity photoId;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
