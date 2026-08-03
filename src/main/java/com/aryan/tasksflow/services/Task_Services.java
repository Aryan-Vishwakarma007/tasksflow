package com.aryan.tasksflow.services;
import com.aryan.tasksflow.dto.AssignGroupTaskRequest;
import com.aryan.tasksflow.entity.Priority;
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

    public Task saveTask(Task myTask){
        return taskRepository.save(myTask);
    }

    public List<Task> findByStatus(){
        return taskRepository.findByStatus();
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
    public List<Task> findByUserIdAndPriority(String userId, Priority priority) {
        return taskRepository.findByUserIdAndPriority(userId, priority);
    }

    public void deleteByuserId(String myId){
        taskRepository.deleteByuserId(myId);
    }
}
