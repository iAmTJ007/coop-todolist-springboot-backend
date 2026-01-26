package com.example.todoservice.repository;

import com.example.todoservice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    //just by extending to jpa repository we get built in methods like save(todo),findById(tid),findAll(),
    //deleteById(tid), existsById(tid)

    List<Todo> findTodosByUsername(String username);
    Todo findTodoByTaskId(Long taskId);
}
