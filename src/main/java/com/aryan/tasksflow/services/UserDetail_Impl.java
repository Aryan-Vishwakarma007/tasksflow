package com.aryan.tasksflow.services;

import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetail_Impl implements UserDetailsService {
    @Autowired
    User_Repository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUser = userRepository.findByusername(username);
        if(byUser != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()

                    .username(byUser.getUsername())
                    .password(byUser.getPassword())
                    .roles(byUser.getRoles().toArray(new String[0]))
                    .build();

            return userDetails;
        }


        return null;
    }
}
