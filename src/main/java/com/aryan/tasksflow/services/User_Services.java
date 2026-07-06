package com.aryan.tasksflow.services;
import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_Services {
    @Autowired
    User_Repository userRepository;

    public List<User> getalls(){
        return userRepository.findAll();
    }

    public void setnew(User myUser){
        userRepository.save(myUser);
    }

    public User findByusername(String username){
        return userRepository.findByusername(username);
    }

    public void deleteUser(String myId){

    }
}
