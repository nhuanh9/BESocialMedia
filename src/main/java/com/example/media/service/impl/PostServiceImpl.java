package com.example.media.service.impl;

import com.example.media.model.entity.PostEntity;
import com.example.media.repository.IPostRepository;
import com.example.media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PostServiceImpl implements PostService {
    @Autowired
    private IPostRepository iPostRepository;

    @Override
    public void savePost(PostEntity postEntity) {
        iPostRepository.save(postEntity);
    }

    @Override
    public void deletePost(PostEntity postEntity) {
        iPostRepository.delete(postEntity);
    }

    @Override
    public void changStatusPostTrue(Long id) {
        iPostRepository.changStatusPostTrue(id);
    }

    @Override
    public void changStatusPostFalse(Long id) {
        iPostRepository.changStatusPostFalse(id);
    }


    @Override
    public void editPost(PostEntity postEntity) {
        iPostRepository.save(postEntity);
    }

    @Override
    public Iterable<PostEntity> findAll() {
        return iPostRepository.findAll();
    }

    @Override
    public Optional<PostEntity> findById(Long id) {
        return iPostRepository.findById(id);
    }
}
