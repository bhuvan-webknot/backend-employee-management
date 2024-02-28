package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.TimesheetNotFoundException;
import com.example.backend.EmployeeManagement.models.Timesheet;
import com.example.backend.EmployeeManagement.repository.TimesheetRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class TimesheetService {
    @Autowired
    TimesheetRepository timesheetRepository;

    final static Logger logger = LoggerFactory.getLogger(TimesheetService.class);

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
    public Timesheet fetchTimesheetById(Long Id) throws TimesheetNotFoundException {
        Optional<Timesheet> timesheetRecord = timesheetRepository.findById(Id);
        if(!timesheetRecord.isPresent()){
            throw new TimesheetNotFoundException("Timesheet with id:"+Id+" not found !!");
        }
        return timesheetRecord.get();
    }

    public Timesheet updateTimesheet(Long tId, Timesheet timesheet) throws TimesheetNotFoundException {
        Optional<Timesheet> isExisting = timesheetRepository.findById(tId);

        if (!isExisting.isPresent()) {
            throw new TimesheetNotFoundException("Employee with id:"+tId+" not found !!");
        }

        Timesheet timesheetDB = isExisting.get();

        if(Objects.nonNull(timesheet.getProjectName()) &&
                !"".equalsIgnoreCase(timesheet.getProjectName())) {
            timesheetDB.setProjectName(timesheet.getProjectName());
        }

        if(Objects.nonNull(timesheet.getEmployeeName()) &&
                !"".equalsIgnoreCase(timesheet.getEmployeeName())) {
            timesheetDB.setEmployeeName(timesheet.getEmployeeName());
        }

        if(!"".equalsIgnoreCase(String.valueOf(timesheet.getDurationHours()))) {
            timesheetDB.setDurationHours(Double.parseDouble(String.valueOf(timesheet.getDurationHours())));
        }

        if(Objects.nonNull(timesheet.getStartDate()) &&
                !"".equalsIgnoreCase(String.valueOf(timesheet.getStartDate()))) {
            timesheetDB.setStartDate(timesheet.getStartDate());
        }

        if(Objects.nonNull(timesheet.getTaskDescription()) &&
                !"".equalsIgnoreCase(timesheet.getTaskDescription())) {
            timesheetDB.setTaskDescription(timesheet.getTaskDescription());
        }

        logger.info("Successfully updated the record !!");

        return timesheetRepository.save(timesheetDB);
    }

    //Delete a record from the table
    public String deleteTimesheet(Long Id) throws TimesheetNotFoundException {
        Optional<Timesheet> timesheetRecord = timesheetRepository.findById(Id);
        if(!timesheetRecord.isPresent()){
            throw new TimesheetNotFoundException("Employee with id:"+Id+" not found !!");
        }
        logger.info("Successfully deleted the record !!");
        timesheetRepository.deleteById(Id);
        return ("Timesheet with id:"+Id+" successfully deleted !!");
    }
}
