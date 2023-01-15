package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToOne(mappedBy = "services",cascade = CascadeType.REMOVE)
    private ServicesButtonEntity button;

}
