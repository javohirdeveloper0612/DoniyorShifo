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
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title_uz;
    @Column(nullable = false)
    private String title_ru;

    @Column(columnDefinition = "text",nullable = false)
    private String description_uz;

    @Column(columnDefinition = "text",nullable = false)
    private String description_ru;

    @OneToOne(cascade = CascadeType.PERSIST)
    private AttachEntity photoId;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;


}
