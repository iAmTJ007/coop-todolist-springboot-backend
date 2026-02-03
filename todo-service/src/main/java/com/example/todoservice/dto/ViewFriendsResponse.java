package com.example.todoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ViewFriendsResponse {
    List<String> friendUsernames;
}
