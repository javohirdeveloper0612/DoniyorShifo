package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private ServicesButtonEntity servicesButtonEntity;

    @OneToOne(mappedBy = "photo")
    @JsonIgnore
    private NewsEntity news;

    @OneToOne(cascade = CascadeType.ALL)
    private AttachContentEntity attachContentEntity;
}
