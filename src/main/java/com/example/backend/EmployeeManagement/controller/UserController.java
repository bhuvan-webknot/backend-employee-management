package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.models.UserEmployee;
import com.example.backend.EmployeeManagement.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
  
    @GetMapping("/list")
    public ResponseEntity<List<UserEmployee>> fetchUserList() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserEmployee> fetchUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create")
    public ResponseEntity<UserEmployee> saveUser(@RequestBody UserEmployee userEmployee) {
        return new ResponseEntity<>(userService.saveUser(userEmployee), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEmployee> updateUser(@PathVariable("id") Long Id, @RequestBody UserEmployee userEmployee) {
        return new ResponseEntity<>(userService.updateUser(Id, userEmployee), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<String>(userService.deleteUser(id), HttpStatusCode.valueOf(200));
    }
}
