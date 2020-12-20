package com.example.media.repository;

import com.example.media.model.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    Optional<FriendEntity> findById(Long id);

    @Query("SELECT f FROM FriendEntity f inner join  f.user u where u.id=:id")
    public List<FriendEntity> findAllFriendById(@Param("id") Long id);
}
