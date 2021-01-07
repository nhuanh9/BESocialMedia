package com.example.media.service;

import com.example.media.model.entity.PostLike;

import java.util.Optional;

public interface PostLikeService {
    void save(PostLike historyEntity);

    void delete(Long id);

    Iterable<PostLike> findAll();

    Optional<PostLike> findById(Long id);
}
