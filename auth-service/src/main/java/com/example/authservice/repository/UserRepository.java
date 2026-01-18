package com.example.authservice.repository;

import com.example.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User,Long>{
 //just by extending jpa repo , we get save(user),findById(id),findAll(),deleteById(id),existsById(id)
    //for login we need to return the user based on his username or email
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    //for ,register we need to check if he exists already based on his username or email
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}