package com.example.todoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PendingFriendRequestsResponse {
    List<String> pendingRequests;
}
