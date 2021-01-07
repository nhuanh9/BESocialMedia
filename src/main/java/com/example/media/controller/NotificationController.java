package com.example.media.controller;

import com.example.media.model.entity.HastagEntity;
import com.example.media.model.entity.NotificationEntity;
import com.example.media.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;


    @GetMapping
    public ResponseEntity<Iterable<NotificationEntity>> getAll() {
        Iterable<NotificationEntity> notificationEntities = notificationService.findAll();
        return new ResponseEntity<>(notificationEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<NotificationEntity>> getById(@PathVariable Long id) {
        Optional<NotificationEntity> notificationEntity1 = this.notificationService.findById(id);
        if (!notificationEntity1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<NotificationEntity> notificationEntity = notificationService.findById(id);
        return new ResponseEntity<>(notificationEntity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody NotificationEntity notificationEntity) {
        notificationService.save(notificationEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody NotificationEntity notificationEntity, @PathVariable Long id) {
        Optional<NotificationEntity> notificationEntity1 = this.notificationService.findById(id);
        if (!notificationEntity1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notificationEntity.setId(notificationEntity1.get().getId());
        notificationService.save(notificationEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
