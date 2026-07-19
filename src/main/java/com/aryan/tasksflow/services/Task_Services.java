package com.aryan.tasksflow.services;
import com.aryan.tasksflow.entity.Task;
import com.aryan.tasksflow.repository.Task_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Task_Services {
    @Autowired
    private Task_Repository taskRepository;

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task setTask(Task myTask){
        return taskRepository.save(myTask);
    }

    public void deleteTask(String MyId){
         taskRepository.deleteById(MyId);
    }

    public Optional<Task> findById(String MyId){
        return taskRepository.findById(MyId);
    }
    public List<Task> findByuserId(String myId){
        return taskRepository.findByuserId(myId);
    }
    public void deleteByuserId(String myId){
        taskRepository.deleteByuserId(myId);
    }
}
