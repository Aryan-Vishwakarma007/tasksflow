package com.aryan.tasksflow.services;

import com.aryan.tasksflow.entity.Group;
import com.aryan.tasksflow.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public Group saveGroup(Group gp){
        return groupRepository.save(gp);
    }
}
