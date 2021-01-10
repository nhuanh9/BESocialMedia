package com.example.media.controller;

import com.example.media.model.PostModel;
import com.example.media.model.User;
import com.example.media.model.entity.ImgEntity;
import com.example.media.model.entity.PostEntity;
import com.example.media.model.entity.PostLike;
import com.example.media.service.IImgService;
import com.example.media.service.PostLikeService;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PostController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private IImgService iImgService;

    @Autowired
    private PostLikeService postLikeService;
    String UPLOADED_FOLDER = "/D:/BESocialMedia/src/main/resources/img/";

    @PostMapping("/addPost/{idUser}")
    public ResponseEntity addPost(@RequestBody PostModel postModel, @PathVariable Long idUser) {
        User user = (userService.findById(idUser)).isPresent() ?
                userService.findById(idUser).get() : null;
        PostEntity postEntity = new PostEntity();
        Date date = new Date(Calendar.getInstance().getTime().getTime());

        postEntity.setCreateAt(date);
        postEntity.setUser(user);
        postEntity.setStatus(postModel.getStatus());
        postEntity.setContent(postModel.getContent());

        ImgEntity imgEntity = new ImgEntity();
        imgEntity.setLinkImg(postModel.getImgs());
        System.out.println("---------------------");
        System.out.println(postModel.getImgs());
        System.out.println("---------------------");
        List<ImgEntity> listImg = new ArrayList<>();
        listImg.add(imgEntity);
        postEntity.setImgs(listImg);

        imgEntity.setUser(user);
        postService.savePost(postEntity);
        List<PostEntity> listPost = (List<PostEntity>) postService.findAll();
        imgEntity.setPostId(listPost.get(listPost.size() - 1).getId());
        iImgService.save(imgEntity);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/editPost")
    public ResponseEntity editPost(@RequestBody PostEntity postEntity, @RequestParam Long idUser) {
        User user = (userService.findById(idUser)).isPresent() ?
                userService.findById(idUser).get() : null;

//        System.out.println("---------------------");
//        System.out.println(img.getOriginalFilename());
//        System.out.println("---------------------");
//
//        if (img.isEmpty()) {
//            System.out.println("nulll");
//        } else {
//            try {
//
//                byte[] bytes = img.getBytes();
//                Path path = Paths.get(UPLOADED_FOLDER + img.getOriginalFilename());
//                Files.write(path, bytes);
//                System.out.println("Upload done");
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        postEntity.setUser(user);
        postService.savePost(postEntity);

        return new ResponseEntity(HttpStatus.OK);
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


    @GetMapping("/posts/{post-id}/post-likes")
    public ResponseEntity<Iterable<PostLike>> getAllLikesByPostId(@PathVariable("post-id") Long postId) {
        Iterable<PostLike> postLike = this.postLikeService.findAllByPostEntityId(postId);
        return new ResponseEntity<>(postLike, HttpStatus.OK);
    }

}
