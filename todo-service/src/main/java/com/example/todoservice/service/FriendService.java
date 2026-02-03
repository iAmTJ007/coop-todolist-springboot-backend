package com.example.todoservice.service;

import com.example.todoservice.dto.PendingFriendRequestsResponse;
import com.example.todoservice.dto.ViewFriendsResponse;
import com.example.todoservice.entity.FriendRequest;
import com.example.todoservice.entity.Fstatus;
import com.example.todoservice.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    private final FriendRequestRepository friendRequestRepository;
    FriendService(FriendRequestRepository friendRequestRepository){
        this.friendRequestRepository=friendRequestRepository;
    }
    //send friend request
    public String sendFriendRequest(String senderUsername,String receiverUsername){
        //if a->b or b->a of pending/accepted already exists then no need of sending again
        if(friendRequestRepository.findByReceiverContainingAndSenderContaining(senderUsername,receiverUsername).isPresent()||friendRequestRepository.findByReceiverContainingAndSenderContaining(receiverUsername,senderUsername).isPresent()){
            throw new RuntimeException("Friend request already exists");
        }
        FriendRequest friendRequest=FriendRequest.builder()
                .sender(senderUsername)
                .receiver(receiverUsername)
                .fstatus(Fstatus.PENDING)
                .build();
        friendRequestRepository.save(friendRequest);
        return "Friend Request Sent";
    }
    //accept friend request
    public String acceptFriendRequest(String receiverUsername,String senderUsername){
        FriendRequest friendRequest=friendRequestRepository.findByReceiverContainingAndSenderContaining(receiverUsername,senderUsername)
                .orElseThrow(()->new RuntimeException("no friend request found"));
        if(friendRequest.getFstatus()!=Fstatus.PENDING){
            throw new RuntimeException("Can only accept a pending request");
        }
        friendRequest.setFstatus(Fstatus.ACCEPTED);
        friendRequestRepository.save(friendRequest);
        return "Friend Request Accepted";
    }
    //reject friend request
    public String rejectFriendRequest(String receiverUsername,String senderUsername){
        FriendRequest friendRequest=friendRequestRepository.findByReceiverContainingAndSenderContaining(receiverUsername,senderUsername)
                .orElseThrow(()->new RuntimeException("no friend request found"));
        if(friendRequest.getFstatus()!=Fstatus.PENDING){
            throw new RuntimeException("Can only reject a pending request");
        }
        friendRequestRepository.delete(friendRequest);
        return "Friend Request Rejected";
        //delete the friend request instead of marking it rejected
    }
    //view all friends
    public ViewFriendsResponse viewFriends(String username){
        //check in the table if username is receiver or sender and status is accepted then take the other one
        List<String> friends=friendRequestRepository.viewFriendList(username);
        return new ViewFriendsResponse(friends);
    }
    //view pending requests
    public PendingFriendRequestsResponse pendingRequests(String username){
        List<String> pending=friendRequestRepository.pendingRequests(username);
        return new PendingFriendRequestsResponse(pending);
    }
}
