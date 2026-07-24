package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.services.User_Services;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class Admin {
    @Autowired
    private User_Services userServices;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userServices.getalls();
        if(users != null){
            return  new ResponseEntity<>(users, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/make-user-admin")
    public  ResponseEntity<?> makeAdmin(@RequestBody String name){
        User user = userServices.findByusername(name);
        user.setRoles(Arrays.asList("ADMIN"));
        userServices.setnew(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("New-admin")
    public ResponseEntity<?> newAdmin(@RequestBody User user){
        userServices.set_admin(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
