package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Entity
@Table(name = "attach-content")
public class AttachContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private byte [] bytes;

    @OneToOne
    private AttachEntity attach;

}
