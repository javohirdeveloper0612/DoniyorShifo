package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "services_button")
public class ServicesButtonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "button_name_ru", nullable = false)
    private String buttonNameRu;

    @Column(name = "button_name_uz", nullable = false)
    private String buttonNameUz;

    @Column(nullable = false)
    private String buttonDescriptionUz;
    @Column(nullable = false)
    private String buttonDescriptionRu;

    @Column(name = "services_id")
    private Integer servicesId;
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "services_id", insertable = false, updatable = false)
    private ServicesEntity services;

    @OneToOne(mappedBy = "button",cascade = CascadeType.REMOVE)
    private ServicesDataEntity dataEntity;

    @Column(updatable = false)
    private LocalDateTime createdDate;


}
