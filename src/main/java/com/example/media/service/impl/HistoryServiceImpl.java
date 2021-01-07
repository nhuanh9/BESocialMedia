package com.example.media.service.impl;

import com.example.media.model.entity.HistoryEntity;
import com.example.media.repository.HastagRepository;
import com.example.media.repository.HistoryRepository;
import com.example.media.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public void save(HistoryEntity historyEntity) {
        historyRepository.save(historyEntity);
    }

    @Override
    public void delete(Long id) {
        historyRepository.deleteById(id);
    }

    @Override
    public Iterable<HistoryEntity> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public Optional<HistoryEntity> findById(Long id) {
        return historyRepository.findById(id);
    }
}
