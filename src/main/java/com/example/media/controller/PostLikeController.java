package com.example.media.controller;

import com.example.media.model.entity.PostHistory;
import com.example.media.model.entity.PostLike;
import com.example.media.service.PostHistoryService;
import com.example.media.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/post-likes")
public class PostLikeController {
    @Autowired
    PostLikeService postLikeService;

    @GetMapping
    public ResponseEntity<Iterable<PostLike>> getAll() {
        Iterable<PostLike> postLikes = postLikeService.findAll();
        return new ResponseEntity<>(postLikes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PostLike>> getById(@PathVariable Long id) {
        Optional<PostLike> postLike = this.postLikeService.findById(id);
        if (!postLike.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postLike, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<PostLike> postLike = this.postLikeService.findById(id);
        if (!postLike.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        postLikeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody PostLike postLike) {
        postLikeService.save(postLike);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody PostLike postLike, @PathVariable Long id) {
        Optional<PostLike> postLike1 = this.postLikeService.findById(id);
        if (!postLike1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postLike.setId(postLike1.get().getId());
        postLikeService.save(postLike);
        return new ResponseEntity(HttpStatus.OK);
    }
}
