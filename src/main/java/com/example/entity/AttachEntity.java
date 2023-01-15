package com.example.entity;

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

    @OneToOne(mappedBy = "attach",cascade = CascadeType.REMOVE)
    private AttachContentEntity attachContent;

}
