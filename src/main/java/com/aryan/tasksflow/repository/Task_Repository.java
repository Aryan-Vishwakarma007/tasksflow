package com.aryan.tasksflow.repository;

import com.aryan.tasksflow.entity.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Task_Repository extends MongoRepository<Task, String> {
//    public Task findById(String myId);
}
