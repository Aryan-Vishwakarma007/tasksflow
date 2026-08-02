package com.aryan.tasksflow.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Document(collection = "group")
@Data
@Component
public class Group {
    private String id;
    private String title;
    private String description;
    private String createdBy;
    private List<String> memberIds;
    private Status status;
    private String comments;
    private Priority priority;

    private LocalDate deadline;

}
//more feature to add---> group mein multiple task honge and unn me se ek hua toh pura group task khatam nhi hoga, saare khatam karo toh task sent fro review hoga and per person ek ek task pe kaam karege