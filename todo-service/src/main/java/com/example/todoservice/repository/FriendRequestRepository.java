package com.example.todoservice.repository;

import com.example.todoservice.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    //save(fid),findById(fid),findAll(),deleteById(fid),existsById(fid)
}
