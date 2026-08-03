package com.aryan.tasksflow.repository;

import com.aryan.tasksflow.dto.AssignGroupTaskRequest;
import com.aryan.tasksflow.entity.Priority;
import com.aryan.tasksflow.entity.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Task_Repository extends MongoRepository<Task, String> {
//    public Task findById(String myId);
        void deleteByuserId(String userId);

//        List<Task> saveGroupTask(AssignGroupTaskRequest tasks);
    List<Task> findByuserId(String myId);

    List<Task> findByUserIdAndPriority(String userId, Priority priority);

    List<Task> findByStatus();
}

