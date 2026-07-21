package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.Task;
import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.repository.User_Repository;
import com.aryan.tasksflow.services.Task_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class Task_Controller {
    @Autowired
    private Task_Services taskServices;
    @Autowired
    private User_Repository userRepository;

    @GetMapping()
    public ResponseEntity<List<Task>> getTasksOfUser(){
        String usernames = "Jerry";
        User byusername = userRepository.findByusername(usernames);

        if(byusername == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Task>  User_Tasks= taskServices.findByuserId(byusername.getId());
        return new ResponseEntity<>(User_Tasks, HttpStatus.OK);

    }

    @PostMapping("/{myId}")
    public ResponseEntity<Task> setTask(@RequestBody Task myTask , @PathVariable String myId){
           try {
               myTask.setUserId(myId);
               taskServices.setTask(myTask);
               return new ResponseEntity<>(myTask, HttpStatus.OK);

           } catch (Exception e) {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
    }
    @Transactional
    @DeleteMapping("/{MyId}")
    public ResponseEntity<?> deleteTask(@PathVariable String MyId){
        taskServices.deleteTask(MyId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{myId}")
    public ResponseEntity<Task> updateTask(@RequestBody Task myTask, @PathVariable String myId){
        Optional<Task> taskkk = taskServices.findById(myId);
        if(taskkk.isPresent()){
            Task task = taskkk.get();  // getting Task form OPTIONAL
            task.setTitle(myTask.getTitle());
            task.setDescription(myTask.getDescription());
            task.setStatus(myTask.getStatus());
            task.setPriority(myTask.getPriority());
            task.setDeadline(myTask.getDeadline());
            task.setUserId((myTask.getUserId()));
            taskServices.setTask(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
