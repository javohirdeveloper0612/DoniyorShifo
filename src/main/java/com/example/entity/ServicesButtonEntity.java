package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Table(name = "services_button")
public class ServicesButtonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "button_name_ru",nullable = false)
    private String buttonName_ru;

    @Column(name = "button_name_uz",nullable = false)
    private String buttonName_uz;

    @Column(nullable = false)
    private String buttonDescription;

    @OneToOne(optional = false)
    private ServicesEntity services;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;


}
