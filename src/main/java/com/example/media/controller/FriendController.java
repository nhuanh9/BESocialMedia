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

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private HqlFriendRepo hqlFriendRepo;


    @GetMapping("/allFriend")
    public ResponseEntity<Iterable<FriendEntity>> getAll() {
        Iterable<FriendEntity> listFriend = friendService.findAll();
        return new ResponseEntity<>(listFriend, HttpStatus.OK);
    }

    @GetMapping("/allFriendById/{idUser}")
    public ResponseEntity<List<User>> getAllFriendById(@PathVariable Long idUser) {
        List<User> listUserFriend = getListUserFriend(idUser);
        return new ResponseEntity<>(listUserFriend, HttpStatus.OK);
    }

    @GetMapping("/allMutualFriend/{idUser1}/{idUser2}")
    public ResponseEntity<List<User>> getMutualFriend(@PathVariable Long idUser1,@PathVariable Long idUser2) {
        List<User> listMutualFriend = new ArrayList<>();
        List<User> listUserFriend1 = getListUserFriend(idUser1);
        List<User> listUserFriend2 = getListUserFriend(idUser2);

        for (User user1: listUserFriend1){
            System.out.println(user1.getId() + "thàng 1");
            for (User user2:listUserFriend2){
                System.out.println(user2.getId() + "thàng 2");
                if (user1.getId() == user2.getId()){
                    listMutualFriend.add(user1);
                }
            }
        }

        return new ResponseEntity<>(listMutualFriend, HttpStatus.OK);
    }

    public List<User> getListUserFriend(Long idUser){
        List<FriendEntity> listFriend = (List<FriendEntity>) friendService.findAllFriendById(idUser);
        List<User> listUser = new ArrayList<>();
        for (FriendEntity friendEntity: listFriend){
            System.out.println("id bạn " + friendEntity.getId());
            if (friendEntity.getStatus() != 0){
                User user = userService.findById(friendEntity.getIdUserFriend()).get();
                listUser.add(user);
            }
        }
        return listUser;
    }

    @GetMapping("/addFriend/{idU1}/{idU2}")
    public ResponseEntity addFriend(@PathVariable Long idU1,@PathVariable Long idU2) {
      User user = userService.findById(idU1).get();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        friendService.save(new FriendEntity(date,1,user,idU2));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/acceptFriend/{idFriend}")
    public ResponseEntity acceptFriend(@PathVariable Long idFriend) {
       FriendEntity friendEntity = friendService.findById(idFriend).get();
       friendEntity.setStatus(1);
       friendService.save(friendEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteFriend/{idU1}/{idU2}")
    public ResponseEntity deleteFriend(@PathVariable Long idU1,@PathVariable Long idU2) {
        FriendEntity friendEntity = friendService.findFriendByIdUser(idU1,idU2);
        System.out.println(friendEntity.getId());
        friendService.delete(friendEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
