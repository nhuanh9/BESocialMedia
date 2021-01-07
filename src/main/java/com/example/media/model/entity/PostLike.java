package com.example.media.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;
}
