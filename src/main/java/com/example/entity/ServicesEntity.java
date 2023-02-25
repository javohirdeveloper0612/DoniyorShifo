package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "services")
public class ServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String nameUz;
    @Column(nullable = false,unique = true)
    private String nameRu;

    @OneToMany(mappedBy = "services",cascade = CascadeType.REMOVE)
    private List<ServicesButtonEntity> button;

}
