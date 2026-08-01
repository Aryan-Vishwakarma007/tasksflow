package com.aryan.tasksflow.dto;

import com.aryan.tasksflow.entity.Priority;
import com.aryan.tasksflow.entity.Status;
import com.aryan.tasksflow.entity.Task;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Data
public class AssignGroupTaskRequest {
    private Task task;
    private List<String> userId;
}
