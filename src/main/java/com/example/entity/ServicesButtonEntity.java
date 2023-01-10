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

    @Column(name = "button_name",nullable = false)
    private String buttonName;

    @Column(nullable = false)
    private String buttonDescription;

    @OneToOne(optional = false)
    private ServicesEntity services;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;


}
