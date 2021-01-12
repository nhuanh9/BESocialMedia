package com.example.media.service;

import com.example.media.model.entity.FriendEntity;
import com.example.media.model.entity.HastagEntity;

import java.util.Optional;

public interface HastagService {
    void save(HastagEntity hastagEntity);

    void delete(Long id);

    Iterable<HastagEntity> findAll();

    Optional<HastagEntity> findById(Long id);
}
