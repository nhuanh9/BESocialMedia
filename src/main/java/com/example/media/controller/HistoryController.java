package com.example.media.controller;

import com.example.media.model.entity.HastagEntity;
import com.example.media.model.entity.HistoryEntity;
import com.example.media.service.HastagService;
import com.example.media.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/histories")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @GetMapping
    public ResponseEntity<Iterable<HistoryEntity>> getAll() {
        Iterable<HistoryEntity> historyEntities = historyService.findAll();
        return new ResponseEntity<>(historyEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<HistoryEntity>> getById(@PathVariable Long id) {
        Optional<HistoryEntity> historyEntity = this.historyService.findById(id);
        if (!historyEntity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(historyEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<HistoryEntity> historyEntity = this.historyService.findById(id);
        if (!historyEntity.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        historyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody HistoryEntity historyEntity) {
        historyService.save(historyEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody HistoryEntity historyEntity, @PathVariable Long id) {
        Optional<HistoryEntity> historyEntity1 = this.historyService.findById(id);
        if (!historyEntity1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        historyEntity.setId(historyEntity1.get().getId());
        historyService.save(historyEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
