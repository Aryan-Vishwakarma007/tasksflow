package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.services.Task_Services;
import com.aryan.tasksflow.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class User_Controller {
    @Autowired
    User_Services userServices;
    @Autowired
    Task_Services taskServices;

    @GetMapping
    public ResponseEntity<List<User>> getall(){
        return new  ResponseEntity<>(userServices.getalls(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> setnew(@RequestBody User myUser){
        try {
            userServices.setnew(myUser);
            return new ResponseEntity<>(myUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{username}")
    public ResponseEntity<User> Update(@RequestBody User myuser, @PathVariable String username){
        User user = userServices.findByusername(username);
        if(user != null){
            user.setUsername(myuser.getUsername());
            user.setEmail(myuser.getEmail());
            user.setPassword(myuser.getPassword());
            userServices.setnew(user);
            return new ResponseEntity<>(user,HttpStatus.OK );
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{myId}")
    public ResponseEntity<?> Delete_By_Id(@PathVariable String myId){
        userServices.deleteUser(myId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
