package com.aryan.tasksflow.services;
import com.aryan.tasksflow.entity.Task;
import com.aryan.tasksflow.repository.Task_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Task_Services {
    @Autowired
    private Task_Repository taskRepository;

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
}
