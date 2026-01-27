package com.example.todoservice.service;

import com.example.todoservice.dto.CreateTodoRequest;
import com.example.todoservice.dto.UpdateTodoRequest;
import com.example.todoservice.entity.Status;
import com.example.todoservice.entity.Todo;
import com.example.todoservice.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository){
        this.todoRepository=todoRepository;
    }

    public List<Todo> getTasks(String username){
        return todoRepository.findTodosByUsername(username);
    }
    public Todo getTaskByTaskId(Long taskId){
        return todoRepository.findTodoByTaskId(taskId);
    }


    public Todo createTask(String username, CreateTodoRequest createTodoRequest){
        Set<String> usernames=new HashSet<>();
        usernames.add(username);
        Todo todo=Todo.builder()
                .usernames(usernames)
                .title(createTodoRequest.getTitle())
                .description(createTodoRequest.getDescription())
                .priority(createTodoRequest.getPriority())
                .status(Status.PENDING)
                .dueDate(createTodoRequest.getDueDate())
                .build();
        todoRepository.save(todo);
        return todo;
    }
    public String deleteTask(Long taskId){
        if(todoRepository.existsById(taskId)){
            todoRepository.deleteById(taskId);
            return "Task Successfully deleted";
        }
        return "Task not found";
    }
    public Todo updateTask(UpdateTodoRequest updateTodoRequest,Long taskId){
        Todo todo=todoRepository.findById(taskId).orElseThrow(()->new RuntimeException("task not found"));
        todo.setTitle(updateTodoRequest.getTitle());
        todo.setDescription(updateTodoRequest.getDescription());
        todo.setPriority(updateTodoRequest.getPriority());
        todo.setDueDate(updateTodoRequest.getDueDate());
        return todoRepository.save(todo);
    }
}
