package com.example.media.service;


import com.example.media.model.entity.FriendEntity;

import java.util.Optional;


public interface FriendService {
    void save(FriendEntity friendEntity);

    void delete(FriendEntity friendEntity);

    Iterable<FriendEntity> findAll();

    Iterable<FriendEntity> findAllFriendById(Long id);

    Optional<FriendEntity> findById(Long id);
}
