package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "services_data")
public class ServicesDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titleUz;
    @Column(nullable = false)
    private String titleRu;

    @Column(nullable = false)
    private String descriptionUz;

    @Column(nullable = false)
    private String descriptionRu;
    @Column(name = "attach_id")
    private Integer attachId;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "button_id")
    private Integer buttonId;
    @OneToOne(optional = false,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "button_id", insertable = false, updatable = false)
    private ServicesButtonEntity button;

    @Column(updatable = false)
    private LocalDateTime createdDate;
}
