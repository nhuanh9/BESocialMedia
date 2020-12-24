package com.example.media.service.impl;

import com.example.media.model.entity.SharePostEntity;
import com.example.media.repository.ISharePostRepository;
import com.example.media.service.SharePostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SharePostServiceImpl implements SharePostService {
    @Autowired
    private ISharePostRepository iSharePostRepository;


    @Override
    public void save(SharePostEntity sharePostEntity) {
        iSharePostRepository.save(sharePostEntity);
    }

    @Override
    public void delete(SharePostEntity sharePostEntity) {
        iSharePostRepository.delete(sharePostEntity);
    }

    @Override
    public void edit(SharePostEntity sharePostEntity) {
        iSharePostRepository.save(sharePostEntity);
    }

    @Override
    public Iterable<SharePostEntity> findAll() {
        return iSharePostRepository.findAll();
    }

    @Override
    public Optional<SharePostEntity> findById(Long id) {
        return iSharePostRepository.findById(id);
    }
}
