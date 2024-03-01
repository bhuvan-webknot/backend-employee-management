package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.exception.TimesheetNotFoundException;
import com.example.backend.EmployeeManagement.models.Employee;
import com.example.backend.EmployeeManagement.models.Timesheet;
import com.example.backend.EmployeeManagement.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TimesheetController {
    @Autowired
    private TimesheetService timesheetService;

    @GetMapping("/list-timesheet")
    public ResponseEntity<List<Timesheet>> fetchTimesheetList() {
        return new ResponseEntity<>(timesheetService.fetchAllTimesheets(),HttpStatusCode.valueOf(200));
    }


    @GetMapping("/timesheet/{id}")
    public ResponseEntity<Timesheet> fetchTimesheetById(@PathVariable Long id) {
        return new ResponseEntity<>(timesheetService.fetchTimesheetById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create-timesheet")
    public ResponseEntity<Timesheet> saveTimesheet(@RequestBody Timesheet timesheet) {
        return new ResponseEntity<>(timesheetService.saveTimesheet(timesheet),HttpStatusCode.valueOf(200));
    }

    @PutMapping("/update-timesheet/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable("id") Long Id, @RequestBody Timesheet timesheet) {
        return new ResponseEntity<>(timesheetService.updateTimesheet(Id,timesheet), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete-timesheet/{id}")
    public ResponseEntity<String> deleteTimesheet(@PathVariable Long id){
        return new ResponseEntity<>(timesheetService.deleteTimesheet(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/sort={field}")
    private ResponseEntity<List<Timesheet>> getTimesheetWithSort(@PathVariable String field) {
        List<Timesheet> allTimesheets = timesheetService.returnTimesheetWithSorting(field);
        return new ResponseEntity<>(allTimesheets,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page={offset}/limit={pageSize}")
    private ResponseEntity<Page<Timesheet>> getTimsheetWithPagination(@PathVariable int offset,
                                                                      @PathVariable int pageSize) {
        Page<Timesheet> timesheetWithPagination = timesheetService.returnTimesheetWithPagination(offset, pageSize);
        return new ResponseEntity<>(timesheetWithPagination,HttpStatusCode.valueOf(200));
    }


    @GetMapping("/page={offset}/limit={pageSize}/sort={field}")
    private ResponseEntity<Page<Timesheet>> getTimesheetWithPaginationAndSort(@PathVariable int offset,
                                                                             @PathVariable int pageSize,@PathVariable String field) {
        Page<Timesheet> timesheetWithPagination = timesheetService.returnTimesheetWithPaginationAndSorting(offset,
                pageSize, field);
        return new ResponseEntity<>(timesheetWithPagination,HttpStatusCode.valueOf(200));
    }

}
