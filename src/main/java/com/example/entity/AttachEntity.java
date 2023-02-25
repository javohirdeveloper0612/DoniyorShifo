package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter

@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GenericGenerator(name = "attach_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String originalName;

    @Column
    private Long size;

    @Column
    private String type;

    @Column
    private String path;

    @OneToOne(mappedBy = "attach", fetch = FetchType.EAGER)
    private ServicesButtonEntity servicesButtonEntity;

    @OneToOne(mappedBy = "photo")
    @JsonIgnore
    private NewsEntity news;


}
