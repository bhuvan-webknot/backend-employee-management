package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.TimesheetNotFoundException;
import com.example.backend.EmployeeManagement.exception.UserNotFoundException;
import com.example.backend.EmployeeManagement.models.Timesheet;
import com.example.backend.EmployeeManagement.models.User;
import com.example.backend.EmployeeManagement.repository.TimesheetRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class TimesheetService {
    @Autowired
    TimesheetRepository timesheetRepository;
    //Creates a entry in the db
    public Timesheet saveTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    //Returns all the timesheets present in the database
    @Cacheable(cacheNames = "timesheets")
    public List<Timesheet> fetchAllTimesheets(){
        return timesheetRepository.findAll();
    }

    //Get an timesheet based on id
    @Cacheable(value = "timesheetWithId",key = "#id")
    public Timesheet fetchTimesheetById(Long id) {
        return timesheetRepository.findById(id)
                .orElseThrow(() -> new TimesheetNotFoundException("Timesheet with ID : " +id+" not found"));
    }

    @CachePut(value = "timesheetWithId",key = "#id")
    @CacheEvict(cacheNames = "timesheets")
    public Timesheet updateTimesheet(Long tId, Timesheet timesheet) {
        Timesheet existingRecord = timesheetRepository.findById(tId)
                .orElseThrow(() -> new TimesheetNotFoundException("Timesheet with ID : " +tId+" not found"));


        if(Objects.nonNull(timesheet.getProjectName()) &&
                !"".equalsIgnoreCase(timesheet.getProjectName())) {
            existingRecord.setProjectName(timesheet.getProjectName());
        }

        if(Objects.nonNull(timesheet.getEmployeeName()) &&
                !"".equalsIgnoreCase(timesheet.getEmployeeName())) {
            existingRecord.setEmployeeName(timesheet.getEmployeeName());
        }

        if(!"".equalsIgnoreCase(String.valueOf(timesheet.getDurationHours()))) {
            existingRecord.setDurationHours(Double.parseDouble(String.valueOf(timesheet.getDurationHours())));
        }

        if(Objects.nonNull(timesheet.getStartDate()) &&
                !"".equalsIgnoreCase(String.valueOf(timesheet.getStartDate()))) {
            existingRecord.setStartDate(timesheet.getStartDate());
        }

        if(Objects.nonNull(timesheet.getTaskDescription()) &&
                !"".equalsIgnoreCase(timesheet.getTaskDescription())) {
            existingRecord.setTaskDescription(timesheet.getTaskDescription());
        }

        log.info("Successfully updated the record !!");

        return timesheetRepository.save(existingRecord);
    }

    //Delete a record from the table
    @CacheEvict(cacheNames = "timesheetWithId", key = "#id",allEntries = true)
    public String deleteTimesheet(Long id) {
        timesheetRepository.findById(id)
                .orElseThrow(() -> new TimesheetNotFoundException("Timesheet with ID : " +id+" not found"));
        log.info("Successfully deleted the record !!");
        timesheetRepository.deleteById(id);
        return ("Timesheet with id:"+id+" successfully deleted !!");
    }

    public List<Timesheet> returnTimesheetWithSorting(String field){
        return  timesheetRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<Timesheet> returnTimesheetWithPagination(int offset, int pageSize){
        return timesheetRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<Timesheet> returnTimesheetWithPaginationAndSorting(int offset,int pageSize,String field){
        return timesheetRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
