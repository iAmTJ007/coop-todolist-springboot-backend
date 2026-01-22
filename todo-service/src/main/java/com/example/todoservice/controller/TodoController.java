package com.example.todoservice.controller;

import com.example.todoservice.dto.CreateTodoRequest;
import com.example.todoservice.entity.Todo;
import com.example.todoservice.service.TodoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {
    public TodoService todoService;
    TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    //create task
    @PostMapping("/createTask")
    public Todo createTask(@RequestBody CreateTodoRequest createTodoRequest, @AuthenticationPrincipal Jwt jwt){
        //first extract username from jwt header then pass username and createTodoRequest to service method
        String username=jwt.getSubject();
        return todoService.createTask(username,createTodoRequest);
    }
}
