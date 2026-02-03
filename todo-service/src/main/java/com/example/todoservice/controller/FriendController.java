package com.example.todoservice.controller;

import com.example.todoservice.dto.PendingFriendRequestsResponse;
import com.example.todoservice.dto.ViewFriendsResponse;
import com.example.todoservice.service.FriendService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;
    FriendController(FriendService friendService){
        this.friendService=friendService;
    }
    //send friend request
    @PostMapping("/sendFriendRequest/{receiverUsername}")
    public String sendFriendRequest(@AuthenticationPrincipal Jwt jwt, @PathVariable String receiverUsername){
        String senderUsername=jwt.getSubject();
        return friendService.sendFriendRequest(senderUsername,receiverUsername);
    }
    //accept friend request
    @PatchMapping("/acceptFriendRequest/{senderUsername}")
    public String acceptFriendRequest(@AuthenticationPrincipal Jwt jwt,@PathVariable String senderUsername){
        String receiverUsername=jwt.getSubject();
        return friendService.acceptFriendRequest(receiverUsername,senderUsername);
    }
    //reject friend request
    @PatchMapping("/rejectFriendRequest/{senderUsername}")
    public String rejectFriendRequest(@AuthenticationPrincipal Jwt jwt,@PathVariable String senderUsername){
        String receiverUsername=jwt.getSubject();
        return friendService.rejectFriendRequest(receiverUsername,senderUsername);
    }
    //view all friends
    @GetMapping("viewFriends/{username}")
    public ViewFriendsResponse viewFriends(@PathVariable String username){
        return friendService.viewFriends(username);
    }
    //view pending requests
    @GetMapping("pendingRequests/{username}")
    public PendingFriendRequestsResponse pendingRequests(@PathVariable String username){
        return friendService.pendingRequests(username);
    }
}
