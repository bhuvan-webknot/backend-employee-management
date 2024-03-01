package com.example.backend.EmployeeManagement.controller;

import com.example.backend.EmployeeManagement.models.UserEmployee;
import com.example.backend.EmployeeManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    UserService userService;
  
    @GetMapping("/list-user")
    public ResponseEntity<List<UserEmployee>> fetchUserList() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserEmployee> fetchUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserEmployee> saveUser(@RequestBody UserEmployee userEmployee) {
        return new ResponseEntity<>(userService.saveUser(userEmployee), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserEmployee> updateUser(@PathVariable("id") Long Id, @RequestBody UserEmployee userEmployee) {
        return new ResponseEntity<>(userService.updateUser(Id, userEmployee), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<String>(userService.deleteUser(id), HttpStatusCode.valueOf(200));
    }
}
