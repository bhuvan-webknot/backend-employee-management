package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.exception.EmployeeAlreadyExistsException;
import com.example.backend.EmployeeManagement.exception.EmployeeNotFoundException;
import com.example.backend.EmployeeManagement.models.Employee;
import com.example.backend.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/api/v1/list-employees")
    public List<Employee> fetchEmployeeList() {
        return employeeService.fetchAllEmployees();
    }


    @GetMapping("/api/v1/employee/{id}")
    public Employee fetchEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.fetchEmployeeById(id);
    }

    @PostMapping("/api/v1/create-employee")
    public Employee saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        if (employeeService.isEmployeeExists(employee.getEmpEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with the same email already exists !!");
        }
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/api/v1/update-employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long Id,@RequestBody Employee employee) throws EmployeeNotFoundException {
        return employeeService.updateEmployee(Id,employee);
    }

    @DeleteMapping("/api/v1/delete-employee/{id}")
    public String deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException{
        return employeeService.deleteEmployee(id);
    }
}
