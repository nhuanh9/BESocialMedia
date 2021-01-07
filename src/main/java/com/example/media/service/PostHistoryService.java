package com.example.media.service;

import com.example.media.model.entity.HistoryEntity;
import com.example.media.model.entity.PostHistory;

import java.util.Optional;

public interface PostHistoryService {
    void save(PostHistory postHistory);

    void delete(Long id);

    Iterable<PostHistory> findAll();

    Optional<PostHistory> findById(Long id);
}
