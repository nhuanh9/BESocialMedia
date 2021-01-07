package com.example.media.service;

import com.example.media.model.entity.HastagEntity;
import com.example.media.model.entity.NotificationEntity;

import java.util.Optional;

public interface NotificationService {
    void save(NotificationEntity notificationEntity);

    void delete(Long id);

    Iterable<NotificationEntity> findAll();

    Optional<NotificationEntity> findById(Long id);
}
