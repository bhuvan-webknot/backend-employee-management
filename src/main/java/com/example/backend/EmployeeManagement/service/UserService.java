package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.UserNotFoundException;
import com.example.backend.EmployeeManagement.models.User;
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
    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setUserPassword(this.passwordEncoder.encode(user.getUserPassword()));
        return this.userRepository.save(user);
    }

    public User fetchUserById(Long Id) {
        User user = userRepository.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("User with ID : " +Id+" not found"));
        return user;
    }

    public User updateUser(Long Id, User user)  {
        User existingUser = userRepository.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("User with ID : " +Id+" not found"));

        if(Objects.nonNull(user.getUserName()) &&
                !"".equalsIgnoreCase(user.getUserName())) {
            existingUser.setUserName(user.getUserName());
        }
        if(Objects.nonNull(user.getUserPassword()) &&
                !"".equalsIgnoreCase(user.getUserPassword())) {
            existingUser.setUserPassword(user.getUserPassword());
        }
        log.info("Successfully updated the record !!");
        return userRepository.save(existingUser);
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
