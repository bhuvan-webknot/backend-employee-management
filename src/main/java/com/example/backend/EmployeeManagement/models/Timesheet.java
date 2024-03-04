package com.example.backend.EmployeeManagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter

public class Timesheet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timesheetId;
    private String projectName;
    private String employeeName;
    private LocalDate startDate;
    private double durationHours;
    private String taskDescription;
}
