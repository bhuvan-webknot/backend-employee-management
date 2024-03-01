package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.EmployeeNotFoundException;
import com.example.backend.EmployeeManagement.models.Employee;
import com.example.backend.EmployeeManagement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("employees")
    public List<Employee> fetchAllEmployees(){
        return employeeRepository.findAll();
    }

    //Get an employee based on id
    @Cacheable(value = "employeeWithId",key = "#id")
    public Employee fetchEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Timesheet with ID : " +id+" not found"));
    }

    //Checks for duplicate email id's
    public boolean isEmployeeExists(String empEmail) {
        return employeeRepository.existsByEmpEmail(empEmail);
    }

    //Update a record :

    @CachePut(value = "employeeWithId",key = "#id")
    @CacheEvict(cacheNames = "employees")
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingRecord = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Timesheet with ID : " +id+" not found"));

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
    @CacheEvict(cacheNames = "employeeWithId", key = "#id",allEntries = true)
    public String deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Timesheet with ID : " +id+" not found"));
        log.info("Successfully deleted the record !!");
        employeeRepository.deleteById(id);
        return ("Employee with id:"+id+" successfully deleted !!");
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
