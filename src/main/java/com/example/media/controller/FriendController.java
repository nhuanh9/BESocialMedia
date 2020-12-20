package com.example.media.controller;

import com.example.media.model.Friend;
import com.example.media.model.User;
import com.example.media.model.entity.FriendEntity;
import com.example.media.repository.HqlFriendRepo;
import com.example.media.service.FriendService;
import com.example.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @Autowired
    private HqlFriendRepo hqlFriendRepo;


    @GetMapping("/allFriend")
    public ResponseEntity<Iterable<FriendEntity>> getAll() {
        Iterable<FriendEntity> listFriend = friendService.findAll();
        return new ResponseEntity<>(listFriend, HttpStatus.OK);
    }

    @GetMapping("/allFriendById/{id}")
    public ResponseEntity<Iterable<Long>> getAllFriendById() {
        List<Long> listFriend = hqlFriendRepo.findAllFriendById(new Long(1));
        return new ResponseEntity<>(listFriend, HttpStatus.OK);
    }


    @PostMapping("/addFriend")
    public ResponseEntity addFriend(@RequestBody Friend friend) {
        User user = (userService.findById(friend.getIdUser())).isPresent() ?
                userService.findById(friend.getIdUser()).get(): null;
        FriendEntity friendEntity = new FriendEntity(friend.getCreateAt(),friend.getStatus(),user, friend.getIdUserFriend());
        if (friendEntity != null){
            friendService.save(friendEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
