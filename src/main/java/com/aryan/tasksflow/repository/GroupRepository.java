package com.aryan.tasksflow.repository;

import com.aryan.tasksflow.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {

}
