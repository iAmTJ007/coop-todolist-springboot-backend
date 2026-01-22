package com.example.todoservice.dto;

import com.example.todoservice.entity.Priority;
import com.example.todoservice.entity.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class UpdateTodoRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    private Priority priority;
    @FutureOrPresent(message = "Due date cannot be in the past")
    private Date dueDate;
}
