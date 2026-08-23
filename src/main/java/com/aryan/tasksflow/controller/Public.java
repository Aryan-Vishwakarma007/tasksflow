package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.services.UserDetailIMPL;
import com.aryan.tasksflow.services.User_Services;
import com.aryan.tasksflow.utills.JWTUtills;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@Slf4j
public class Public {
    @Autowired
    User_Services userServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JWTUtills jwtUtills;
    @Autowired
    UserDetailIMPL userDetailIMPL;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody User myUser){
        try {
            userServices.setnewUser(myUser);
            return new ResponseEntity<>(myUser, HttpStatus.OK);

        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User myUser){
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(myUser.getUsername(), myUser.getPassword()));
            // above line will while logging in kya user name and password sahi h ki nhii
            UserDetails userDetails = userDetailIMPL.loadUserByUsername(myUser.getUsername());
            // user ki detailss dedo bhai
            String jwt = jwtUtills.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while creating token", e);
            return new ResponseEntity<>("Incorrect Name or Password", HttpStatus.BAD_REQUEST);
        }
    }
}
