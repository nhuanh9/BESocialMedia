package com.example.media.controller;

import com.example.media.model.User;
import com.example.media.model.entity.PostEntity;
import com.example.media.service.PostService;
import com.example.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PostController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    String UPLOADED_FOLDER = "/D:/BESocialMedia/src/main/resources/img/";

    @PostMapping("/addPost")
    public ResponseEntity addPost(@RequestBody PostEntity postEntity, @RequestParam Long idUser,@RequestParam MultipartFile img) {
        User user = (userService.findById(idUser)).isPresent() ?
                userService.findById(idUser).get() : null;

        System.out.println("---------------------");
        System.out.println(img.getOriginalFilename());
        System.out.println("---------------------");

        if (img.isEmpty()) {
            System.out.println("nulll");
        } else {
            try {

                byte[] bytes = img.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + img.getOriginalFilename());
                Files.write(path, bytes);
                System.out.println("Upload done");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        postEntity.setUser(user);
        postEntity.setImg(img.getOriginalFilename());
        postService.savePost(postEntity);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/editPost")
    public ResponseEntity editPost(@RequestBody PostEntity postEntity, @RequestParam Long idUser,@RequestParam MultipartFile img) {
        User user = (userService.findById(idUser)).isPresent() ?
                userService.findById(idUser).get() : null;

        System.out.println("---------------------");
        System.out.println(img.getOriginalFilename());
        System.out.println("---------------------");

        if (img.isEmpty()) {
            System.out.println("nulll");
        } else {
            try {

                byte[] bytes = img.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + img.getOriginalFilename());
                Files.write(path, bytes);
                System.out.println("Upload done");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        postEntity.setUser(user);
        postEntity.setImg(img.getOriginalFilename());
        postService.savePost(postEntity);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/allPost")
    public ResponseEntity<Iterable<PostEntity>> getAll() {
        Iterable<PostEntity> listPost = postService.findAll();
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @GetMapping("/allPostByUserId/{idUser}")
    public ResponseEntity<Iterable<PostEntity>> getAllByUserId(@PathVariable Long idUser) {
        Iterable<PostEntity> listPost = postService.findAllByUserId(idUser);
        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @GetMapping("/findPostById/{idPost}")
    public ResponseEntity<PostEntity> findPostById(@PathVariable Long idPost) {
        PostEntity post = postService.findById(idPost).get();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/changStatusTrue/{idPost}")
    public ResponseEntity changStatusTrue(@PathVariable Long idPost) {
        postService.changStatusPostTrue(idPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/changStatusFalse/{idPost}")
    public ResponseEntity changStatusFalse(@PathVariable Long idPost) {
        postService.changStatusPostFalse(idPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deletePost/{idPost}")
    public ResponseEntity deletePost(@PathVariable Long idPost) {
        PostEntity postEntity = postService.findById(idPost).get();
        postService.deletePost(postEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
