package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Table(name = "resume")
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String description_uz;
    @Column
    private String description_ru;

    @OneToOne(cascade = CascadeType.PERSIST)
    private AttachEntity attach;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

}
