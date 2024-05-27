package com.fit.se.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor@AllArgsConstructor
@Table(name = "employees")
@Getter@Setter
public class Employee implements Serializable {

    private static final long serialVersionUID = -7715423091552062591L;;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    @Column(nullable = false, unique = true)
    private String email;
    @ManyToOne
    private Department department;
}
