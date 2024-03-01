package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.exception.EmployeeAlreadyExistsException;
import com.example.backend.EmployeeManagement.exception.EmployeeNotFoundException;
import com.example.backend.EmployeeManagement.models.Employee;
import com.example.backend.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> fetchEmployeeList() {
        return new ResponseEntity<>(employeeService.fetchAllEmployees(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")

    public ResponseEntity<Employee> fetchEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.fetchEmployeeById(id),HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create")

    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        if (employeeService.isEmployeeExists(employee.getEmpEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with the same email already exists !!");
        }
        return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatusCode.valueOf(200));
    }


    @PutMapping("/update/{id}")


    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long Id,@RequestBody Employee employee)  {
        return new ResponseEntity<>(employeeService.updateEmployee(Id,employee),HttpStatusCode.valueOf(200));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.deleteEmployee(id),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/sort={field}")
    private ResponseEntity<List<Employee>> getEmployeesWithSort(@PathVariable String field) {
        List<Employee> allProducts = employeeService.returnEmployeesWithSorting(field);
        return new ResponseEntity<>(allProducts,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page={offset}/limit={pageSize}")
    private ResponseEntity<Page<Employee>> getEmployeesWithPagination(@PathVariable int offset,
                                                                   @PathVariable int pageSize) {
        Page<Employee> employeesWithPagination = employeeService.returnEmployeesWithPagination(offset, pageSize);
        return new ResponseEntity<>(employeesWithPagination,HttpStatusCode.valueOf(200));
    }


    @GetMapping("/page={offset}/limit={pageSize}/sort={field}")
    private ResponseEntity<Page<Employee>> getEmployeesWithPaginationAndSort(@PathVariable int offset,
                                                                   @PathVariable int pageSize,@PathVariable String field) {
        Page<Employee> employeesWithPagination = employeeService.returnEmployeesWithPaginationAndSorting(offset,
                pageSize, field);
        return new ResponseEntity<>(employeesWithPagination,HttpStatusCode.valueOf(200));
    }



}
