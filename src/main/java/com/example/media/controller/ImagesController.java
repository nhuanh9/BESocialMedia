package com.example.media.controller;

import com.example.media.model.entity.FriendEntity;
import com.example.media.model.entity.ImgEntity;
import com.example.media.service.IImgService;
import com.example.media.service.PostService;
import com.example.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ImagesController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private IImgService iImgService;

    @GetMapping("/imagesByUser/{idUser}")
    public ResponseEntity<Iterable<ImgEntity>> getAllByUser(@PathVariable Long idUser) {
        Iterable<ImgEntity> listImg = iImgService.findAllByUserId(idUser);
        return new ResponseEntity<>(listImg, HttpStatus.OK);
    }

    @GetMapping("/imagesByPost/{idPost}")
    public ResponseEntity<Iterable<ImgEntity>> getAllByPost(@PathVariable Long idPost) {
        Iterable<ImgEntity> listImg = iImgService.findAllByPostId(idPost);
        return new ResponseEntity<>(listImg, HttpStatus.OK);
    }
}
