package com.ruben.backend_to_do_list.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    private String id;

    private String title;
    private String description;
    private boolean completed;

    private String category;
    private Priority priority;

    private LocalDateTime createdAt;
    private LocalDateTime dueDate;

    private TaskStatus status;


}