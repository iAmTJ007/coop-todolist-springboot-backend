package com.example.todoservice.repository;

import com.example.todoservice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    //just by extending to jpa repository we get built in methods like save(todo),findById(tid),findAll(),
    //deleteById(tid), existsById(tid)
}
