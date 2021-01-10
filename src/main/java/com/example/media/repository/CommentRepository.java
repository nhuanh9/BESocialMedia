package com.example.media.repository;

import com.example.media.model.entity.CommentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentForm,Long> {
}
