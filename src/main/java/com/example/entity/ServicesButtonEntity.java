package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String titleUz;
    @Column(nullable = false)
    private String titleRu;

    @Column(nullable = false)
    private String buttonDescriptionUz;
    @Column(nullable = false)
    private String buttonDescriptionRu;

    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "services_id")
    private Integer servicesId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "services_id", insertable = false, updatable = false)
    private ServicesEntity services;


}
