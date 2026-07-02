package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class User_Controller {
    @Autowired
    User_Services userServices;

    @GetMapping
    public List<User> getall(){
        userServices.getalls();
    }
}
