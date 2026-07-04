package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.Task;
import com.aryan.tasksflow.services.Task_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Task_Controller {
    @Autowired
    private Task_Services taskServices;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        return new ResponseEntity<>(taskServices.getTasks(), HttpStatus.OK);
    }

}
