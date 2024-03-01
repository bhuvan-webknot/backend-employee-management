package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.TimesheetNotFoundException;
import com.example.backend.EmployeeManagement.exception.UserNotFoundException;
import com.example.backend.EmployeeManagement.models.Timesheet;
import com.example.backend.EmployeeManagement.models.User;
import com.example.backend.EmployeeManagement.repository.TimesheetRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class TimesheetService {
    @Autowired
    TimesheetRepository timesheetRepository;
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    //Creates a entry in the db
    public Timesheet saveTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    //Returns all the timesheets present in the database
    public List<Timesheet> fetchAllTimesheets(){
        return timesheetRepository.findAll();
    }

    //Get an timesheet based on id
    public Timesheet fetchTimesheetById(Long Id) {
        return timesheetRepository.findById(Id)
                .orElseThrow(() -> new TimesheetNotFoundException("Timesheet with ID : " +Id+" not found"));
    }

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
    public String deleteTimesheet(Long Id) {
        timesheetRepository.findById(Id)
                .orElseThrow(() -> new TimesheetNotFoundException("Timesheet with ID : " +Id+" not found"));
        log.info("Successfully deleted the record !!");
        timesheetRepository.deleteById(Id);
        return ("Timesheet with id:"+Id+" successfully deleted !!");
    }
}
