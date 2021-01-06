package com.example.media.model.entity;

import com.example.media.model.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "images")
public class ImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 1000)
    private String linkImg;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    public ImgEntity() {
    }

    public ImgEntity(Long id, String linkImg, User user, PostEntity postEntity) {
        this.id = id;
        this.linkImg = linkImg;
        this.user = user;
        this.postEntity = postEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PostEntity getPostEntity() {
        return postEntity;
    }

    public void setPostEntity(PostEntity postEntity) {
        this.postEntity = postEntity;
    }
}
