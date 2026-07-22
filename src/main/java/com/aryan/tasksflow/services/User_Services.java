package com.aryan.tasksflow.services;
import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_Services {
    @Autowired
    User_Repository userRepository;

    @Autowired
    Task_Services taskServices;

    private static final PasswordEncoder encoderr = new BCryptPasswordEncoder();

    public List<User> getalls(){
        return userRepository.findAll();
    }

    public void setnew(User myUser){
        userRepository.save(myUser);
    }

    public void  setnewUser(User Myuser){

        Myuser.setPassword(encoderr.encode(Myuser.getPassword()));
        userRepository.save(Myuser);

    }

    public User findByusername(String username){
        return userRepository.findByusername(username);
    }

    public void deleteUser(String myId){
        taskServices.deleteByuserId(myId);
        userRepository.deleteByid(myId);
    }
}
