package com.example.todoservice.repository;

import com.example.todoservice.entity.Status;
import com.example.todoservice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    //just by extending to jpa repository we get built in methods like save(todo),findById(tid),findAll(),
    //deleteById(tid), existsById(tid)

    List<Todo> findTodosByUsernamesContaining(String username);


    //find pending todos for a particular user
    @Query(
            """
        select t from Todo t join t.usernames u where u=:username and t.status=:status
"""
    )
    List<Todo> findTodosByUsernameAndStatus(String username, Status status);

    Todo findTodoByTaskId(Long taskId);
}
