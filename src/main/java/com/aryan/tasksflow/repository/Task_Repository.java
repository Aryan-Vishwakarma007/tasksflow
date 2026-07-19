package com.aryan.tasksflow.repository;

import com.aryan.tasksflow.entity.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Task_Repository extends MongoRepository<Task, String> {
//    public Task findById(String myId);
        void deleteByuserId(String userId);

    List<Task> findByuserId(String myId);
}
