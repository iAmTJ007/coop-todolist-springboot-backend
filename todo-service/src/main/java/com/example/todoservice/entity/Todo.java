package com.example.todoservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column(nullable = false)
    private String username; //we will get this from jwt
    @Column(nullable = false,unique = true)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Status status; //pending,completed defined in enum
    @Column(nullable = false)
    private Priority priority; //low,medium,high defined in enum
    @Column(nullable=false)
    private LocalDate dueDate;

}
