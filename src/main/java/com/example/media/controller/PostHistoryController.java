package com.example.media.controller;

import com.example.media.model.entity.HistoryEntity;
import com.example.media.model.entity.PostHistory;
import com.example.media.service.HistoryService;
import com.example.media.service.PostHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/post-histories")
public class PostHistoryController {
    @Autowired
    PostHistoryService postHistoryService;

    @GetMapping
    public ResponseEntity<Iterable<PostHistory>> getAll() {
        Iterable<PostHistory> postHistories = postHistoryService.findAll();
        return new ResponseEntity<>(postHistories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PostHistory>> getById(@PathVariable Long id) {
        Optional<PostHistory> postHistory = this.postHistoryService.findById(id);
        if (!postHistory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<PostHistory> postHistory = this.postHistoryService.findById(id);
        if (!postHistory.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        postHistoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody PostHistory postHistory) {
        postHistoryService.save(postHistory);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody PostHistory postHistory, @PathVariable Long id) {
        Optional<PostHistory> postHistory1 = this.postHistoryService.findById(id);
        if (!postHistory1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postHistory.setId(postHistory1.get().getId());
        postHistoryService.save(postHistory);
        return new ResponseEntity(HttpStatus.OK);
    }
}
