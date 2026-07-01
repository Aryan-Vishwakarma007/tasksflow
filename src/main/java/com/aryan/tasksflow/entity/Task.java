package com.aryan.tasksflow.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "tasks")
@Data
public class Task {
    @Id
    private String id;

    private String title;

    private String description;

    private String status;

    private String priority;

    private LocalDate deadline;

    private String userId;


}
