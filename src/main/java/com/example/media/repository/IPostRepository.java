package com.example.media.repository;

import com.example.media.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("UPDATE PostEntity SET status = 1 WHERE (id =: id)")
    void changStatusPostTrue(@Param("id") Long id);

    @Query("UPDATE PostEntity SET status = 0 WHERE (id =: id)")
    void changStatusPostFalse(@Param("id") Long id);

    Optional<PostEntity> findById(Long id);
}
