package com.aryan.tasksflow.dto;

import com.aryan.tasksflow.entity.Status;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UpdateStatus {
    private Status currStatus;
}
