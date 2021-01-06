package com.example.media.service;

import com.example.media.model.entity.ImgEntity;
import com.example.media.model.entity.PostEntity;

import java.util.Optional;

public interface IImgService {
    void save(ImgEntity imgEntity);

    void delete(ImgEntity imgEntity);

    void edit(ImgEntity imgEntity);

    Iterable<ImgEntity> findAll();

    Iterable<ImgEntity> findAllByUserId(Long idUser);

    Iterable<ImgEntity> findAllByPostId(Long idUser);

    Optional<ImgEntity> findById(Long id);
}
