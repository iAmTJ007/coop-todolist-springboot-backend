package com.example.todoservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteTodoRequest {
    @NotBlank
    private Long taskId;
}
