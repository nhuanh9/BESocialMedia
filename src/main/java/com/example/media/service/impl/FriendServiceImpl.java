package com.example.media.service.impl;

import com.example.media.model.entity.FriendEntity;
import com.example.media.repository.FriendRepository;
import com.example.media.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;
    @Override
    public void save(FriendEntity friendEntity) {
        friendRepository.save(friendEntity);
    }

    @Override
    public Iterable<FriendEntity> findAll() {
        return friendRepository.findAll();
    }
}
