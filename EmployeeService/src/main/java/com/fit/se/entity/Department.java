package com.fit.se.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Table(name = "departments")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;

}
