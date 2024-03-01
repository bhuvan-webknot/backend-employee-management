package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.UserNotFoundException;
import com.example.backend.EmployeeManagement.models.UserEmployee;
import com.example.backend.EmployeeManagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEmployee> fetchAllUsers(){
        return userRepository.findAll();
    }

    public UserEmployee saveUser(UserEmployee userEmployee) {
        userEmployee.setUserPassword(this.passwordEncoder.encode(userEmployee.getUserPassword()));
        return this.userRepository.save(userEmployee);
    }

    public UserEmployee fetchUserById(Long Id) {
        UserEmployee userEmployee = userRepository.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("User with ID : " +Id+" not found"));
        return userEmployee;
    }

    public UserEmployee updateUser(Long Id, UserEmployee userEmployee)  {
        UserEmployee existingUserEmployee = userRepository.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("User with ID : " +Id+" not found"));

        if(Objects.nonNull(userEmployee.getUserName()) &&
                !"".equalsIgnoreCase(userEmployee.getUserName())) {
            existingUserEmployee.setUserName(userEmployee.getUserName());
        }
        if(Objects.nonNull(userEmployee.getUserPassword()) &&
                !"".equalsIgnoreCase(userEmployee.getUserPassword())) {
            existingUserEmployee.setUserPassword(userEmployee.getUserPassword());
        }

        log.info("Successfully updated the record !!");
        return userRepository.save(existingUserEmployee);
    }

    //Delete a record from the table
    public String deleteUser(Long Id) {
        userRepository.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("User with ID : " +Id+" not found"));
        log.info("Successfully deleted the record !!");
        userRepository.deleteById(Id);
        return ("User with id:"+Id+" successfully deleted !!");
    }

}
