package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.dto.AssignGroupTaskRequest;
import com.aryan.tasksflow.dto.MakeAdminRequset;
import com.aryan.tasksflow.entity.Task;
import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.services.Task_Services;
import com.aryan.tasksflow.services.User_Services;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class Admin {
    @Autowired
    private User_Services userServices;
    @Autowired
    private Task_Services taskServices;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userServices.getalls();
        if(users != null){
            return  new ResponseEntity<>(users, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/make-user-admin")
    public  ResponseEntity<?> makeAdmin(@RequestBody MakeAdminRequset entity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String name = entity.getUsername();
        User user = userServices.findByusername(name);
        user.setRoles(Arrays.asList("ADMIN"));
        userServices.setnew(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/New-admin")
    public ResponseEntity<?> newAdmin(@RequestBody User user){
        userServices.set_admin(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/assign-tasks/{id}")
    public ResponseEntity<?> newTask(@RequestBody Task myTask, @PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        myTask.setUserId(id);
        taskServices.saveTask(myTask);
        return new ResponseEntity<>(myTask, HttpStatus.OK);
    }

    @PostMapping("/assign-group-task")
    public ResponseEntity<?> newgroup(@RequestBody AssignGroupTaskRequest tasks){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        taskServices.saveTasks(tasks);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
