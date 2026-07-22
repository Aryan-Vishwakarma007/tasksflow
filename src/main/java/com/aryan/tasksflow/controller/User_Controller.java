package com.aryan.tasksflow.controller;

import com.aryan.tasksflow.entity.Task;
import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.services.Task_Services;
import com.aryan.tasksflow.services.User_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class User_Controller {
    @Autowired
    User_Services userServices;
    @Autowired
    Task_Services taskServices;

    @GetMapping()
    public ResponseEntity<List<Task>> get_task_User(){
        System.out.println("done1");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("done2");
        String usernames = authentication.getName();
        System.out.println(usernames);
        User user = userServices.findByusername(usernames);
        System.out.println("done3");
        List<Task> tasks = taskServices.findByuserId(user.getId());
        System.out.println("done4");
        return new ResponseEntity<>(tasks, HttpStatus.FOUND);

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
    @Transactional
    @DeleteMapping("/{myId}")
    public ResponseEntity<?> Delete_By_Id(@PathVariable String myId){
        userServices.deleteUser(myId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
