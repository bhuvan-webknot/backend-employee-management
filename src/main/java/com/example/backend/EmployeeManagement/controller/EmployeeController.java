package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.exception.EmployeeAlreadyExistsException;
import com.example.backend.EmployeeManagement.exception.EmployeeNotFoundException;
import com.example.backend.EmployeeManagement.models.Employee;
import com.example.backend.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list-employees")
    public ResponseEntity<List<Employee>> fetchEmployeeList() {
        return new ResponseEntity<>(employeeService.fetchAllEmployees(), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> fetchEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.fetchEmployeeById(id),HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create-employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        if (employeeService.isEmployeeExists(employee.getEmpEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with the same email already exists !!");
        }
        return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatusCode.valueOf(200));
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long Id,@RequestBody Employee employee) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.updateEmployee(Id,employee),HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException{
        return new ResponseEntity<>(employeeService.deleteEmployee(id),HttpStatusCode.valueOf(200));
    }
}
