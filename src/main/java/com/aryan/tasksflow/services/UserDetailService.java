package com.aryan.tasksflow.services;
import com.aryan.tasksflow.entity.User;
import com.aryan.tasksflow.entity.UserPrinciple;
import com.aryan.tasksflow.repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    User_Repository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username);

        if(user == null){
//            return new UsernameNotFoundException("not found");
            return null;
        }

        return new UserPrinciple(user);
    }
}
