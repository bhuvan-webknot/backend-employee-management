package com.example.backend.EmployeeManagement.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Employee {
    @Id

    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String empName;
    private String empRole;
    private String empPhone;
    @Column(unique = true)
    private String empEmail;
    private String empDesignation;
    private double salary;
}
