package com.example.media.service.impl;

import com.example.media.model.entity.FriendEntity;
import com.example.media.repository.FriendRepository;
import com.example.media.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;
    @Override
    public void save(FriendEntity friendEntity) {
        friendRepository.save(friendEntity);
    }

    @Override
    public void delete(FriendEntity friendEntity) {
        friendRepository.delete(friendEntity);
    }

    @Override
    public Iterable<FriendEntity> findAll() {
        return friendRepository.findAll();
    }

    @Override
    public Iterable<FriendEntity> findAllFriendById(Long id) {
        return friendRepository.findAllFriendById(id);
    }

    @Override
    public Optional<FriendEntity> findById(Long id) {
        return friendRepository.findById(id);
    }

    @Override
    public FriendEntity findFriendByIdUser(Long idF, Long idU) {
        return friendRepository.findByUserIdAndIdUserFriend(idF,idU);
    }
}
