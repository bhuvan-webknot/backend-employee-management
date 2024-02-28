package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.exception.UserNotFoundException;
import com.example.backend.EmployeeManagement.models.User;
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
    public ResponseEntity<List<User>> fetchUserList() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long Id,@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(Id,user), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotFoundException{
        return new ResponseEntity<String>(userService.deleteUser(id), HttpStatusCode.valueOf(200));
    }
}
