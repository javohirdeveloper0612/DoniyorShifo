package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Table(name = "doctor_tashkent")
public class DoctorsTashkentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firsName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String speciality;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false , name ="experience" )
    private Integer experience;

    @Column(columnDefinition = "text",nullable = false , name ="description" )
    private String description;

    @OneToOne
    private AttachEntity photoId;

    @CreationTimestamp
    @Column(updatable = false , name = "created_date")
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
