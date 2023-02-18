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
@Table(name = "attach-content")
public class AttachContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private byte[] bytes;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AttachEntity attach;

}
