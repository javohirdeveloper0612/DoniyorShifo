package com.example.entity;
import com.example.enums.AdminRole;
import com.example.enums.AdminStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column
    private AdminStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column
    private AdminRole role;
}
