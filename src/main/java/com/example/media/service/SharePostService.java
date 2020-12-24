package com.example.media.service;

import com.example.media.model.entity.SharePostEntity;

import java.util.Optional;


public interface SharePostService {
    void save(SharePostEntity sharePostEntity);

    void delete(SharePostEntity sharePostEntity);

    void edit(SharePostEntity sharePostEntity);

    Iterable<SharePostEntity> findAll();

    Optional<SharePostEntity> findById(Long id);

}
