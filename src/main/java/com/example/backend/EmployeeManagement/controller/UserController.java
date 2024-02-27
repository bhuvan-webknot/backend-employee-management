package com.example.backend.EmployeeManagement.controller;


import com.example.backend.EmployeeManagement.exception.UserNotFoundException;
import com.example.backend.EmployeeManagement.models.User;
import com.example.backend.EmployeeManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    
    @GetMapping("/api/v1/list-user")
    public List<User> fetchUserList() {
        return userService.fetchAllUsers();
    }


    @GetMapping("/api/v1/user/{id}")
    public User fetchUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.fetchUserById(id);
    }

    @PostMapping("/api/v1/create-user")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/api/v1/update-user/{id}")
    public User updateUser(@PathVariable("id") Long Id,@RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(Id,user);
    }

    @DeleteMapping("/api/v1/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) throws UserNotFoundException{
        return userService.deleteUser(id);
    }
}
