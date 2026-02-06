package com.example.todoservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddUserToTaskRequest {
    @NotBlank(message = "no username found in dto request")
    private String username;
}
