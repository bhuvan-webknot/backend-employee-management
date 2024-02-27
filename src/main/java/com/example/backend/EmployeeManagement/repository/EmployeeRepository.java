package com.example.backend.EmployeeManagement.repository;

import com.example.backend.EmployeeManagement.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmpEmail(String empEmail);
}
