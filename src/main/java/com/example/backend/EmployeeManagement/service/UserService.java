package com.example.backend.EmployeeManagement.service;


import com.example.backend.EmployeeManagement.exception.UserNotFoundException;
import com.example.backend.EmployeeManagement.models.User;
import com.example.backend.EmployeeManagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    final static Logger logger = LoggerFactory.getLogger(TimesheetService.class);
    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return (User) userRepository.save(user);
    }

    public User fetchUserById(Long Id) throws UserNotFoundException {
        Optional<User> userRecord = userRepository.findById(Id);
        if(!userRecord.isPresent()){
            throw new UserNotFoundException("User with id:"+Id+" not found !!");
        }
        return userRecord.get();
    }

    public User updateUser(Long tId, User user) throws UserNotFoundException {
        Optional<User> isExisting = userRepository.findById(tId);

        if (!isExisting.isPresent()) {
            throw new UserNotFoundException("Employee with id:"+tId+" not found !!");
        }

        User userDB = isExisting.get();

        if(Objects.nonNull(user.getUserName()) &&
                !"".equalsIgnoreCase(user.getUserName())) {
            userDB.setUserName(user.getUserName());
        }
        if(Objects.nonNull(user.getUserPassword()) &&
                !"".equalsIgnoreCase(user.getUserPassword())) {
            userDB.setUserPassword(user.getUserPassword());
        }
        logger.info("Successfully updated the record !!");
        return (User) userRepository.save(userDB);
    }

    //Delete a record from the table
    public String deleteUser(Long Id) throws UserNotFoundException {
        Optional<User> userRecord = userRepository.findById(Id);
        if(!userRecord.isPresent()){
            throw new UserNotFoundException("User with id:"+Id+" not found !!");
        }
        logger.info("Successfully deleted the record !!");
        userRepository.deleteById(Id);
        return ("User with id:"+Id+" successfully deleted !!");
    }

}
