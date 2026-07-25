package com.aryan.tasksflow.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

@Document(collection = "tasks")
@Data
public class Task {
    @Id
    private String id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDate deadline;

    private String userId;


}
