package com.example.media.repository;

import com.example.media.model.entity.FriendEntity;
import com.example.media.model.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImgRepo extends JpaRepository<ImgEntity, Long> {
    public Iterable<ImgEntity> findAllByUserId(Long userId);
//    public Iterable<ImgEntity> findAllByPostEntityId(Long postId);
}
