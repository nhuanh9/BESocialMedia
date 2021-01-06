package com.example.media.service.impl;

import com.example.media.model.entity.HastagEntity;
import com.example.media.repository.HastagRepository;
import com.example.media.service.HastagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HastagServiceImpl implements HastagService {
    @Autowired
    HastagRepository hastagRepository;

    @Override
    public void save(HastagEntity hastagEntity) {
        hastagRepository.save(hastagEntity);
    }

    @Override
    public void delete(Long id) {
        hastagRepository.deleteById(id);
    }

    @Override
    public Iterable<HastagEntity> findAll() {
        return hastagRepository.findAll();
    }

    @Override
    public Optional<HastagEntity> findById(Long id) {
        return hastagRepository.findById(id);
    }
}
