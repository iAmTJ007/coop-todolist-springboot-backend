package com.example.todoservice.service;

import com.example.todoservice.dto.CreateTodoRequest;
import com.example.todoservice.entity.Status;
import com.example.todoservice.entity.Todo;
import com.example.todoservice.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository){
        this.todoRepository=todoRepository;
    }
    public Todo createTask(String username, CreateTodoRequest createTodoRequest){
        Todo todo=Todo.builder()
                .username(username)
                .title(createTodoRequest.getTitle())
                .description(createTodoRequest.getDescription())
                .priority(createTodoRequest.getPriority())
                .status(Status.PENDING)
                .dueDate(createTodoRequest.getDueDate())
                .build();
        todoRepository.save(todo);
        return todo;
    }
}
