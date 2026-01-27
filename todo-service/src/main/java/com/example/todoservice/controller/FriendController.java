package com.example.todoservice.controller;

import com.example.todoservice.service.FriendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendController {
    FriendService friendService;
    FriendController(FriendService friendService){
        this.friendService=friendService;
    }

}
