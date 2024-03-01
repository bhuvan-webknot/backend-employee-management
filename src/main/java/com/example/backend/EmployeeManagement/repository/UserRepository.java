package com.example.backend.EmployeeManagement.repository;

import com.example.backend.EmployeeManagement.models.UserEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEmployee,Long> {
}
