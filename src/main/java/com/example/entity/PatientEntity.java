package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String phone;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    private DoctorEntity doctorId;

    @CreationTimestamp
    private Timestamp createdDate;

}
