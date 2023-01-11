package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.service.annotation.GetExchange;

import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Table(name = "services_data")
public class ServicesDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title_uz;
    @Column(nullable = false)
    private String title_ru;

    @Column(nullable = false)
    private String description_uz;

    @Column(nullable = false)
    private String description_ru;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    private AttachEntity attach;

    @OneToOne(optional = false)
    private ServicesButtonEntity button;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
}
