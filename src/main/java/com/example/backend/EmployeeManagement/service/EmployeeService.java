package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.EmployeeNotFoundException;
import com.example.backend.EmployeeManagement.models.Employee;
import com.example.backend.EmployeeManagement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    //Creates a entry in the db
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    //Returns all the employees present in the database
    public List<Employee> fetchAllEmployees(){
        return employeeRepository.findAll();
    }

    //Get an employee based on id
    public Employee fetchEmployeeById(Long Id) {
        return employeeRepository.findById(Id)
                .orElseThrow(() -> new EmployeeNotFoundException("Timesheet with ID : " +Id+" not found"));
    }

    //Checks for duplicate email id's
    public boolean isEmployeeExists(String empEmail) {
        return employeeRepository.existsByEmpEmail(empEmail);
    }

    //Update a record :
    public Employee updateEmployee(Long empId, Employee employee) {
        Employee existingRecord = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Timesheet with ID : " +empId+" not found"));

        if(Objects.nonNull(employee.getEmpName()) &&
                !"".equalsIgnoreCase(employee.getEmpName())) {
            existingRecord.setEmpName(employee.getEmpName());
        }

        if(Objects.nonNull(employee.getEmpEmail()) &&
                !"".equalsIgnoreCase(employee.getEmpEmail())) {
            existingRecord.setEmpEmail(employee.getEmpEmail());
        }

        if(Objects.nonNull(employee.getEmpPhone()) &&
                !"".equalsIgnoreCase(employee.getEmpPhone())) {
            existingRecord.setEmpPhone(employee.getEmpPhone());
        }

        if(Objects.nonNull(employee.getEmpRole()) &&
                !"".equalsIgnoreCase(employee.getEmpRole())) {
            existingRecord.setEmpRole(employee.getEmpRole());
        }

        if(Objects.nonNull(employee.getEmpDept()) &&
                !"".equalsIgnoreCase(employee.getEmpDept())) {
            existingRecord.setEmpDept(employee.getEmpDept());
        }

        if(Objects.nonNull(employee.getStartingDate()) &&
                !"".equalsIgnoreCase(String.valueOf(employee.getStartingDate()))) {
            existingRecord.setEmpDept(String.valueOf(employee.getStartingDate()));
        }

        if(Objects.nonNull(employee.getLeavingDate()) &&
                !"".equalsIgnoreCase(String.valueOf(employee.getLeavingDate()))) {
            existingRecord.setEmpDept(String.valueOf(employee.getLeavingDate()));
        }

        if(!"".equalsIgnoreCase(String.valueOf(employee.getSalary()))) {
            existingRecord.setSalary(Double.parseDouble(String.valueOf((employee.getSalary()))));
        }

        log.info("Successfully updated the record !!");

        return employeeRepository.save(existingRecord);
    }

    //Delete a record from the table
    public String deleteEmployee(Long Id) {
        employeeRepository.findById(Id)
                .orElseThrow(() -> new EmployeeNotFoundException("Timesheet with ID : " +Id+" not found"));
        log.info("Successfully deleted the record !!");
        employeeRepository.deleteById(Id);
        return ("Employee with id:"+Id+" successfully deleted !!");
    }


    public List<Employee> returnEmployeesWithSorting(String field){
        return  employeeRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<Employee> returnEmployeesWithPagination(int offset, int pageSize){
        return employeeRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<Employee> returnEmployeesWithPaginationAndSorting(int offset,int pageSize,String field){
        return employeeRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
