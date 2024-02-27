package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.exception.TimesheetNotFoundException;
import com.example.backend.EmployeeManagement.models.Timesheet;
import com.example.backend.EmployeeManagement.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimesheetController {
    @Autowired
    private TimesheetService timesheetService;

    @GetMapping("/api/v1/list-timesheet")
    public List<Timesheet> fetchTimesheetList() {
        return timesheetService.fetchAllTimesheets();
    }


    @GetMapping("/api/v1/timesheet/{id}")
    public Timesheet fetchTimesheetById(@PathVariable Long id) throws TimesheetNotFoundException {
        return timesheetService.fetchTimesheetById(id);
    }

    @PostMapping("/api/v1/create-timesheet")
    public Timesheet saveTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.saveTimesheet(timesheet);
    }

    @PutMapping("/api/v1/update-timesheet/{id}")
    public Timesheet updateTimesheet(@PathVariable("id") Long Id,@RequestBody Timesheet timesheet) throws TimesheetNotFoundException {
        return timesheetService.updateTimesheet(Id,timesheet);
    }

    @DeleteMapping("/api/v1/delete-timesheet/{id}")
    public String deleteTimesheet(@PathVariable Long id) throws TimesheetNotFoundException{
        return timesheetService.deleteTimesheet(id);
    }
}
