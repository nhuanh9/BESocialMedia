package com.example.media.service.impl;

import com.example.media.model.entity.NotificationEntity;
import com.example.media.repository.NotificationRepository;
import com.example.media.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public void save(NotificationEntity notificationEntity) {
        notificationRepository.save(notificationEntity);
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Iterable<NotificationEntity> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<NotificationEntity> findById(Long id) {
        return notificationRepository.findById(id);
    }
}
