package com.aryan.tasksflow.repository;

import com.aryan.tasksflow.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface User_Repository extends MongoRepository<User, ObjectId> {
    public User findByusername(String username);
}
