package com.example.todoservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(
            name = "todo_users",
            joinColumns = @JoinColumn(name = "taskId")
    )
    @Column(name = "username")
    private Set<String> usernames; //we will get this from jwt
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; //pending,completed defined in enum
    @Column(nullable = false)
    private Priority priority; //low,medium,high defined in enum
    @Column(nullable=false)
    private LocalDate dueDate;

}
