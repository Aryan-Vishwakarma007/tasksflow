package com.aryan.tasksflow.services;

import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Service
public class UserDetailIMPL implements UserDetailsService {
    @Autowired
    User_Repository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUser = userRepository.findByusername(username);
        if(byUser != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()

                    .username(byUser.getUsername())
                    .password(byUser.getPassword())
                    .build();

            return userDetails;
        }


        throw new UsernameNotFoundException("User Not Found!") ;
    }
}
