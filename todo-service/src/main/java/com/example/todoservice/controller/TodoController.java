package com.example.todoservice.controller;

import com.example.todoservice.dto.AddUserToTaskRequest;
import com.example.todoservice.dto.CreateTodoRequest;
import com.example.todoservice.dto.DeleteTodoRequest;
import com.example.todoservice.dto.UpdateTodoRequest;
import com.example.todoservice.entity.Todo;
import com.example.todoservice.service.TodoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    public TodoService todoService;
    TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    //get tasks for that user
    @GetMapping("/getTask")
    public List<Todo> getTasks(@AuthenticationPrincipal Jwt jwt){
        return todoService.getTasks(jwt.getSubject());
    }
    //get task by task id
    @GetMapping("/getTask/{taskId}")
    public Todo getTaskById(@PathVariable Long taskId){
        return todoService.getTaskByTaskId(taskId);
    }


    //create task
    @PostMapping("/createTask")
    public Todo createTask(@RequestBody CreateTodoRequest createTodoRequest, @AuthenticationPrincipal Jwt jwt){
        //first extract username from jwt header then pass username and createTodoRequest to service method
        String username=jwt.getSubject();
        return todoService.createTask(username,createTodoRequest);
    }
    //delete task
    @DeleteMapping("/deleteTask")
    public String deleteTask(@RequestBody DeleteTodoRequest deleteTodoRequest){
        return todoService.deleteTask(deleteTodoRequest.getTaskId());
    }
    //update task
    @PutMapping("/updateTask/{taskId}")
    public Todo updateTask(@RequestBody UpdateTodoRequest updateTodoRequest,@PathVariable Long taskId){
        return todoService.updateTask(updateTodoRequest,taskId);
    }
    //add user to a task
    @PatchMapping("/addUser/{taskId}")
    public String addUserToTask(@RequestBody AddUserToTaskRequest addUserToTaskRequest,@PathVariable Long taskId){
        return todoService.addUserToTask(addUserToTaskRequest.getUsername(),taskId);
    }

}
