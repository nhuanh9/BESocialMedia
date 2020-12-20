package com.example.media.service;


import com.example.media.model.entity.FriendEntity;


public interface FriendService {
    void save(FriendEntity friendEntity);

    Iterable<FriendEntity> findAll();
}
