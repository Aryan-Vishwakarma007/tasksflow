package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.Status;
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
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class Task_Controller {
    @Autowired
    private Task_Services taskServices;
    @Autowired
    private User_Repository userRepository;

    @GetMapping()
    public  ResponseEntity<List<Task>> setTask() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User byusername = userRepository.findByusername(name);
        String id = byusername.getId();
        List<Task> byuserId = taskServices.findByuserId(id);
        return new ResponseEntity<>(byuserId, HttpStatus.FOUND);

    }
    @PostMapping()
    public ResponseEntity<Task> setTask(@RequestBody Task myTask ){
           try {
               Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
               String username = authentication.getName();
               User user = userRepository.findByusername(username);
               myTask.setUserId(user.getId());
               taskServices.saveTask(myTask);
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
            taskServices.saveTask(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //if ur currently working on a task and wanna change the status to IN_PROGESS
    @PostMapping("{taskId}/status")
    public ResponseEntity<?> updateStatus(@RequestBody Status Mystatus, @PathVariable String taskId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByusername(username);
        Optional<Task> task = taskServices.findById(taskId);
        if (task.isPresent()){
            Task avail_tasks = task.get();
            String userId = avail_tasks.getUserId();
            if(Objects.equals(user.getId(), userId)){
                avail_tasks.setStatus(Mystatus);
                return new ResponseEntity<>(avail_tasks, HttpStatus.OK);
            }
        }
         return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No tasks found :(");

    }
    @PostMapping("/task-review/{myId}")
    public ResponseEntity<?> sendTask_Review(@RequestBody Status myStatus , @PathVariable String myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Task> taskkk = taskServices.findById(myId);
        if(taskkk.isPresent()){
            Task task = taskkk.get();
            if(task.getUserId() == myId){
                task.setStatus(Status.SUBMITTED_FOR_REVIEW);
                return new ResponseEntity<>(task, HttpStatus.OK);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No tasks found :(");

    }


}
