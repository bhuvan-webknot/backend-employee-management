package com.example.backend.EmployeeManagement.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


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
    private String empDept;
    @Column(unique = true)
    private String empEmail;
    private double salary;
    private Date startingDate;
    private Date leavingDate;
}
