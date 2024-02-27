package com.example.backend.EmployeeManagement.repository;

import com.example.backend.EmployeeManagement.models.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet,Long> {

}
