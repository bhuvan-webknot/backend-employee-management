package com.example.backend.EmployeeManagement.repository;

import com.example.backend.EmployeeManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
