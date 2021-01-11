package com.example.media.service;

import com.example.media.model.entity.PostEntity;

import java.util.Optional;

public interface PostService {
    void savePost(PostEntity postEntity);

    void deletePost(PostEntity postEntity);

    void changStatusPostTrue(Long id);

    void changStatusPostFalse(Long id);

    void editPost(PostEntity postEntity);

    Iterable<PostEntity> findAll();

    Iterable<PostEntity> findAllByStatus(int status);

    Iterable<PostEntity> findAllByUserId(Long idUser);

    Iterable<PostEntity> findAllByContent(String content,Long id);

    Optional<PostEntity> findById(Long id);
}
