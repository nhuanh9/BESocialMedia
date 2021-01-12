package com.example.media.service.impl;

import com.example.media.model.UserLikePost;
import com.example.media.model.entity.PostLike;
import com.example.media.repository.PostLikeRepository;
import com.example.media.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    @Autowired
    PostLikeRepository likePostRepository;

    @Override
    public void save(PostLike postLike) {
        likePostRepository.save(postLike);
    }

    @Override
    public void delete(Long id) {
        likePostRepository.deleteById(id);
    }

    @Override
    public Iterable<PostLike> findAll() {
        return likePostRepository.findAll();
    }

    @Override
    public Optional<PostLike> findById(Long id) {
        return likePostRepository.findById(id);
    }

    @Override
    public PostLike findByUserIdAndPostEntityId(Long userId, Long postId) {
        return likePostRepository.findByUserIdAndPostEntityId(userId, postId);
    }

    @Override
    public Iterable<PostLike> findAllByPostEntityId(Long postId) {
        return likePostRepository.findAllByPostEntityId(postId);
    }

    @Override
    public void deleteAllByPostEntityId(Long id) {
        likePostRepository.deleteAllByPostEntityId(id);
    }
}
