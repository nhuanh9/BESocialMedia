package com.example.media.service;

import com.example.media.model.entity.HistoryEntity;
import com.example.media.model.entity.NotificationEntity;

import java.util.Optional;

public interface HistoryService {
    void save(HistoryEntity historyEntity);

    void delete(Long id);

    Iterable<HistoryEntity> findAll();

    Optional<HistoryEntity> findById(Long id);
}
