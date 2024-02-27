package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.EmployeeNotFoundException;
import com.example.backend.EmployeeManagement.models.Employee;
import com.example.backend.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    //Creates a entry in the db
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    //Returns all the employees present in the database
    public List<Employee> fetchAllEmployees(){
        return employeeRepository.findAll();
    }

    //Get an employee based on id
    public Employee fetchEmployeeById(Long Id) throws EmployeeNotFoundException {
        Optional<Employee> employeeRecord = employeeRepository.findById(Id);
        if(!employeeRecord.isPresent()){
            throw new EmployeeNotFoundException("Employee with id:"+Id+" not found !!");
        }
        return employeeRecord.get();
    }

    //Checks for duplicate email id's
    public boolean isEmployeeExists(String empEmail) {
        return employeeRepository.existsByEmpEmail(empEmail);
    }

    //Update a record :
    public Employee updateEmployee(Long empId, Employee employee) throws EmployeeNotFoundException {
        Optional<Employee> isExisting = employeeRepository.findById(empId);

        if (!isExisting.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id:"+empId+" not found !!");
        }

        Employee empDB = isExisting.get();

        if(Objects.nonNull(employee.getEmpName()) &&
                !"".equalsIgnoreCase(employee.getEmpName())) {
            empDB.setEmpName(employee.getEmpName());
        }

        if(Objects.nonNull(employee.getEmpEmail()) &&
                !"".equalsIgnoreCase(employee.getEmpEmail())) {
            empDB.setEmpEmail(employee.getEmpEmail());
        }

        if(Objects.nonNull(employee.getEmpPhone()) &&
                !"".equalsIgnoreCase(employee.getEmpPhone())) {
            empDB.setEmpPhone(employee.getEmpPhone());
        }

        if(Objects.nonNull(employee.getEmpRole()) &&
                !"".equalsIgnoreCase(employee.getEmpRole())) {
            empDB.setEmpRole(employee.getEmpRole());
        }

        if(Objects.nonNull(employee.getEmpDesignation()) &&
                !"".equalsIgnoreCase(employee.getEmpDesignation())) {
            empDB.setEmpDesignation(employee.getEmpDesignation());
        }

        if(Objects.nonNull(employee.getSalary()) &&
                !"".equalsIgnoreCase(String.valueOf(employee.getSalary()))) {
            empDB.setSalary(Double.parseDouble(String.valueOf((employee.getSalary()))));
        }

        logger.info("Successfully updated the record !!");

        return employeeRepository.save(empDB);
    }

    //Delete a record from the table
    public String deleteEmployee(Long Id) throws EmployeeNotFoundException {
        Optional<Employee> employeeRecord = employeeRepository.findById(Id);
        if(!employeeRecord.isPresent()){
            throw new EmployeeNotFoundException("Employee with id:"+Id+" not found !!");
        }
        logger.info("Successfully deleted the record !!");
        employeeRepository.deleteById(Id);
        return ("Employee with id:"+Id+" successfully deleted !!");
    }

}
