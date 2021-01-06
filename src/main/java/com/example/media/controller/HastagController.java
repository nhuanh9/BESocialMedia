package com.example.media.controller;

import com.example.media.model.User;
import com.example.media.model.entity.HastagEntity;
import com.example.media.model.entity.PostEntity;
import com.example.media.service.HastagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/hastags")
public class HastagController {

    @Autowired
    HastagService hastagService;

    @GetMapping
    public ResponseEntity<Iterable<HastagEntity>> getAll() {
        Iterable<HastagEntity> hastagEntities = hastagService.findAll();
        return new ResponseEntity<>(hastagEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<HastagEntity>> getById(@PathVariable Long id) {
        Optional<HastagEntity> hastagEntity1 = this.hastagService.findById(id);
        if (!hastagEntity1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<HastagEntity> hastagEntity = hastagService.findById(id);
        return new ResponseEntity<>(hastagEntity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody HastagEntity hastagEntity) {
        hastagService.save(hastagEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody HastagEntity hastagEntity, @PathVariable Long id) {
        Optional<HastagEntity> hastagEntity1 = this.hastagService.findById(id);
        if (!hastagEntity1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hastagEntity.setId(hastagEntity1.get().getId());
        hastagService.save(hastagEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

}