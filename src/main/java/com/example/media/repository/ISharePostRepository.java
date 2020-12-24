package com.example.media.repository;

import com.example.media.model.entity.SharePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISharePostRepository extends JpaRepository<SharePostEntity, Long> {
    Optional<SharePostEntity> findById(Long id);
}
