package com.example.media.service.impl;

import com.example.media.model.entity.PostHistory;
import com.example.media.repository.PostHistoryRepository;
import com.example.media.service.PostHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostHistoryServiceImpl implements PostHistoryService {

    @Autowired
    PostHistoryRepository postHistoryRepository;

    @Override
    public void save(PostHistory postHistory) {
        postHistoryRepository.save(postHistory);
    }

    @Override
    public void delete(Long id) {
        postHistoryRepository.deleteById(id);
    }

    @Override
    public Iterable<PostHistory> findAll() {
        return postHistoryRepository.findAll();
    }

    @Override
    public Optional<PostHistory> findById(Long id) {
        return postHistoryRepository.findById(id);
    }
}
