package com.example.media.model.entity;

import com.example.media.model.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "postHistory")
public class PostHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;


    private String content;

    private String img;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;
}
