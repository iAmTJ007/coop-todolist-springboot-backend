package com.example.todoservice.repository;

import com.example.todoservice.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    //save(fid),findById(fid),findAll(),deleteById(fid),existsById(fid)

    public Optional<FriendRequest> findByReceiverContainingAndSenderContaining(String receiverUsername, String senderUsername);
    @Query(
            """
        select\s
            case
                when fr.sender=:username then fr.receiver
                else fr.sender
            end
        from FriendRequest fr
        where fr.fstatus='ACCEPTED' and (fr.sender=:username or fr.receiver=:username)
"""
    )
    //if username is sender and status is accepted then choose receiver and if username is receiver and
    //status is accepted then choose sender
    public List<String> viewFriendList(@Param("username") String username);

    @Query(
            """
        select fr.sender from FriendRequest fr where fr.receiver=:username and fr.fstatus='PENDING'
"""
    )
    public List<String> pendingRequests(@Param("username") String username);
}
