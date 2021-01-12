package com.example.media.model.entity;

import com.example.media.model.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class CommentForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Date createAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
