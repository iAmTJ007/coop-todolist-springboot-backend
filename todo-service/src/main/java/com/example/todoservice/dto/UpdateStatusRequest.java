package com.example.todoservice.dto;

import com.example.todoservice.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    @NotNull
    private Status status;
}
