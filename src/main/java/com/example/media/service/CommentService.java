package com.example.media.service;

import com.example.media.model.entity.CommentForm;

import java.util.Optional;

public interface CommentService {
    Iterable<CommentForm> findAll();

    Optional<CommentForm> findById(Long id);

    void save(CommentForm commentForm);

    void delete(Long id);
}
