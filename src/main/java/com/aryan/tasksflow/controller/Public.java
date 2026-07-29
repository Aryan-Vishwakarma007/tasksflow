package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class Public {
    @Autowired
    User_Services userServices;

    @PostMapping()
    public ResponseEntity<?> setnew(@RequestBody User myUser){
        try {
            userServices.setnewUser(myUser);
            return new ResponseEntity<>(myUser, HttpStatus.OK);

        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!!");
        }
    }
}
