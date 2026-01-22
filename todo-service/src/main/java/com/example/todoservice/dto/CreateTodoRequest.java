package com.example.todoservice.dto;

import com.example.todoservice.entity.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTodoRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    @NotNull
    private Priority priority;
    @FutureOrPresent(message = "Due date cannot be in the past")
    @NotNull
    private LocalDate dueDate;
}
