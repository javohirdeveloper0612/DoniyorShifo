package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String originalName;

    @Column
    private Long size;

    @Column
    private String type;


    @OneToOne(mappedBy = "attach", fetch = FetchType.EAGER)
    private ServicesDataEntity servicesDataEntity;

    @OneToOne(mappedBy = "photoId")
    @JsonIgnore
    private NewsEntity news;

}
